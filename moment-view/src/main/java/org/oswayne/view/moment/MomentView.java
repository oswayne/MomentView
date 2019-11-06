package org.oswayne.view.moment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.oswayne.view.moment.provider.CommentProvider;
import org.oswayne.view.moment.provider.MomentProvider;

import java.util.Collection;
import java.util.List;

public final class MomentView extends ViewGroup {

    private RecyclerView mMomentRecyclerView;
    private MomentViewAdapter mMomentAdapter;

    public MomentView(Context context) {
        super(context);
        init();
    }

    public MomentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mMomentRecyclerView.layout(getPaddingLeft(), getPaddingTop(), getMeasuredWidth(), getMeasuredHeight());
    }

    private void init() {
        mMomentRecyclerView = new RecyclerView(getContext());
        mMomentRecyclerView.setLayoutParams(generateDefaultLayoutParams());
        mMomentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMomentRecyclerView.addItemDecoration(new DecorationLine());
        mMomentAdapter = new MomentViewAdapter();
        mMomentAdapter.bindToRecyclerView(mMomentRecyclerView);
        addView(mMomentRecyclerView);
    }

    public void setData(List<MomentProvider> data) {
        mMomentAdapter.setNewData(data);
    }

    public void setOnPraiseListener(OnPraiseListener onPraiseListener) {
        mMomentAdapter.setOnPraiseListener(onPraiseListener);
    }

    public void setOnCommentListener(OnCommentListener onCommentListener) {
        mMomentAdapter.setOnCommentListener(onCommentListener);
    }

    public void addPraiseData(int index, String praise) {
        mMomentAdapter.addPraiseData(index, praise);
    }

    public void addPraiseData(int index, Collection<String> praise) {
        mMomentAdapter.addPraiseData(index, praise);
    }

    public void addCommentData(int index, CommentProvider commentProvider) {
        mMomentAdapter.addCommentData(index, commentProvider);
    }

    public void addCommentData(int index, Collection<CommentProvider> commentProviders) {
        mMomentAdapter.addCommentData(index, commentProviders);
    }

    /**
     * 点赞回调接口
     */
    public interface OnPraiseListener {
        void onPraise(int position, MomentProvider item);
    }

    /**
     * 评论回调接口
     */
    public interface OnCommentListener {
        void onComment(int position, MomentProvider item, String comment);
    }
}
