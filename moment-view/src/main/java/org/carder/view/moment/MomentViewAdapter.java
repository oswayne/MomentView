package org.carder.view.moment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.carder.view.AutoGridView;
import org.carder.view.R;

import java.util.Collections;
import java.util.List;

class MomentViewAdapter extends BaseQuickAdapter<MomentProvider, BaseViewHolder> {

    private MomentView.OnPraiseListener mOnPraiseListener;
    private MomentView.OnCommentListener mOnCommentListener;

    MomentViewAdapter() {
        super(R.layout.moment_item_layout, Collections.<MomentProvider>emptyList());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, final MomentProvider item) {
        // Render Step1: User avatar, name or title, release time
        setDefaultInfo(helper, item);
        // Render Step2: Content ,Location, Type or description, Interaction data
        setOtherInfo(helper, item);

        helper.getView(R.id.btn_operation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOperationMenu(v, item);
            }
        });
    }

    /**
     * 设置基本信息，消息 Item 的必备信息，如标题、媒体信息、用户头像等
     */
    private void setDefaultInfo(BaseViewHolder helper, MomentProvider item) {
        ImageView avatarImageView = helper.getView(R.id.iv_avatar);
        AutoGridView autoGridView = helper.getView(R.id.agv_media);
        TextView titleTextView = helper.getView(R.id.tv_title);
        TextView timeTextView = helper.getView(R.id.tv_time);

        item.setAvatar(avatarImageView);
        titleTextView.setText(item.getTitle());
        autoGridView.setAdapter(item.getMediaViewAdapter());
        timeTextView.setText(item.getTime());
    }

    /**
     * 设置一些非必要信息，如文本内容、位置信息、类型及点赞和评论等信息
     */
    private void setOtherInfo(BaseViewHolder helper, MomentProvider item) {
        String contentText = item.getContent();
        TextView contentTextView = helper.getView(R.id.tv_content);
        if (contentText == null || contentText.isEmpty()) {
            contentTextView.setVisibility(View.GONE);
        } else {
            contentTextView.setText(contentText);
        }

        String locateText = item.getLocate();
        TextView locateTextView = helper.getView(R.id.tv_locate);
        if (locateText == null || locateText.isEmpty()) {
            locateTextView.setVisibility(View.GONE);
        } else {
            locateTextView.setText(locateText);
        }

        String typeText = item.getType();
        TextView typeTextView = helper.getView(R.id.tv_type);
        if (typeText == null || typeText.isEmpty()) {
            typeTextView.setVisibility(View.GONE);
        } else {
            typeTextView.setText(typeText);
        }

        setPraise((TextView) helper.getView(R.id.tv_praise), item.getPraises());

        setComment((GridLayout) helper.getView(R.id.gl_comment), item.getComments());
    }

    /**
     * 设置点赞信息
     */
    private void setPraise(TextView praiseTextView, List<String> praises) {
        if (praises == null || praises.isEmpty()) {
            praiseTextView.setVisibility(View.GONE);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String praise : praises) {
            stringBuilder.append(praise);
            stringBuilder.append("，");
        }
        praiseTextView.setText(stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    /**
     * 设置评论互动信息
     */
    private void setComment(GridLayout commentGridLayout, List<CommentProvider> comments) {
        if (comments == null || comments.isEmpty()) {
            commentGridLayout.setVisibility(View.GONE);
            return;
        }
        for (CommentProvider commentProvider : comments) {
            switch (commentProvider.getType()) {
                case DEF_COMMENT:
                    setDefaultCommentView(commentGridLayout, commentProvider);
                    break;
                case REPLY_COMMENT:
                    setReplyCommentView(commentGridLayout, commentProvider);
                    break;
            }
        }
    }

    private void setDefaultCommentView(GridLayout rootView, CommentProvider commentProvider) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.moment_item_def_comment, rootView, false);
        TextView userTextView = view.findViewById(R.id.tv_user);
        userTextView.setText(commentProvider.getUsername());

        TextView contentTextView = view.findViewById(R.id.tv_content);
        contentTextView.setText(commentProvider.getContent());
        rootView.addView(view);
    }

    private void setReplyCommentView(GridLayout rootView, CommentProvider commentProvider) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.moment_item_reply_comment, rootView, false);
        TextView userTextView = view.findViewById(R.id.tv_user);
        userTextView.setText(commentProvider.getUsername());

        TextView replyTextView = view.findViewById(R.id.tv_reply);
        replyTextView.setText(commentProvider.getReplyUsername());

        TextView contentTextView = view.findViewById(R.id.tv_content);
        contentTextView.setText(commentProvider.getContent());
        rootView.addView(view);
    }

    /**
     * 显示操作菜单
     */
    private void showOperationMenu(View anchor, MomentProvider item) {
        PopupMenu popupMenu = new PopupMenu(mContext, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.memont_operation_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener(mContext, getRecyclerView(), mOnPraiseListener, mOnCommentListener, item));
        popupMenu.show();
    }

    void setOnPraiseListener(MomentView.OnPraiseListener mOnPraiseListener) {
        this.mOnPraiseListener = mOnPraiseListener;
    }

    void setOnCommentListener(MomentView.OnCommentListener mOnCommentListener) {
        this.mOnCommentListener = mOnCommentListener;
    }

    /**
     * 评论互动菜单点击监听
     */
    private static class OnMenuItemClickListener
            implements PopupMenu.OnMenuItemClickListener, TextView.OnEditorActionListener, View.OnClickListener, PopupWindow.OnDismissListener {

        private MomentView.OnPraiseListener mOnPraiseListener;
        private MomentView.OnCommentListener mOnCommentListener;
        private MomentProvider mMomentProvider;

        private Context mContext;
        private PopupWindow mCommentInputWindow;
        private RecyclerView mRecyclerView;
        private EditText mCommentEditText;

        /**
         * 这段代码可能看起来像个傻逼，但是请诸位先听我解释：
         * 想要在弹出 PopupMenu 的同时一起弹出软键盘是不可行的，软键盘必须延迟 150 ms 以上弹出，
         * 延迟时间可能与性能有关，暂未做更多的测试，在 MI 4 LTE 机型上测得 150 ms 可以正常弹出。
         */
        private Handler mShowSoftKeyboardHandler;
        private InputMethodManager mInputMethodManager;

        OnMenuItemClickListener(Context context, RecyclerView recyclerView, MomentView.OnPraiseListener onPraiseListener, MomentView.OnCommentListener onCommentListener, MomentProvider momentProvider) {
            this.mContext = context;
            this.mRecyclerView = recyclerView;
            this.mOnPraiseListener = onPraiseListener;
            this.mOnCommentListener = onCommentListener;
            this.mMomentProvider = momentProvider;
            this.mShowSoftKeyboardHandler = new Handler();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.action_menu_thumb_up) {
                if (mOnPraiseListener != null) {
                    mOnPraiseListener.onPraise(mMomentProvider);
                    return true;
                }
            } else if (menuItem.getItemId() == R.id.action_menu_comment) {
                if (mOnCommentListener != null) {
                    showCommentInputView();
                    return true;
                }
            }
            return false;
        }

        private void showCommentInputView() {
            if (mCommentInputWindow == null) {
                initCommentInputView();
            }
            mCommentInputWindow.showAsDropDown(mRecyclerView);
            showSoftKeyboard();
        }

        private void initCommentInputView() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.moment_item_layout_comment, mRecyclerView, false);
            mCommentInputWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            mCommentEditText = view.findViewById(R.id.edt_comment);
            Button submitButton = view.findViewById(R.id.btn_comment);
            mCommentInputWindow.setTouchable(true);
            mCommentInputWindow.setFocusable(true);
            mCommentInputWindow.setOutsideTouchable(true);
            mCommentInputWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
            mCommentInputWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            mCommentInputWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            mCommentInputWindow.update();

            mCommentEditText.setOnEditorActionListener(this);
            submitButton.setOnClickListener(this);
            mCommentInputWindow.setOnDismissListener(this);
        }

        private void showSoftKeyboard() {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            mShowSoftKeyboardHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mInputMethodManager.showSoftInput(mCommentEditText, 0);
                }
            }, 150);
        }

        private void onCommentSend() {
            mOnCommentListener.onComment(mMomentProvider, mCommentEditText.getText().toString());
            mCommentInputWindow.dismiss();
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onCommentSend();
                return true;
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_comment) {
                onCommentSend();
            }
        }

        @Override
        public void onDismiss() {
            mInputMethodManager.hideSoftInputFromWindow(mCommentEditText.getWindowToken(), 0);
        }
    }
}
