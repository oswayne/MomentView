package org.carder.view;

import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Collections;
import java.util.List;

class MomentViewAdapter extends BaseQuickAdapter<MomentProvider, BaseViewHolder> {

    MomentViewAdapter() {
        super(R.layout.moment_item_layout, Collections.<MomentProvider>emptyList());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MomentProvider item) {
        // Render Step1: User avatar, name or title, release time
        setDefaultInfo(helper, item);
        // Render Step2: Content ,Location, Type or description, Interaction data
        setOtherInfo(helper, item);
    }

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

    private void setPraise(TextView praiseTextView, List<String> praises) {
        if (praises == null || praises.isEmpty()) {
            praiseTextView.setVisibility(View.GONE);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String praise : praises) {
            stringBuilder.append(praise);
            stringBuilder.append("、");
        }
        praiseTextView.setText(stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    private void setComment(GridLayout commentGridLayout, List<String> comments) {
        if (comments == null || comments.isEmpty()) {
            commentGridLayout.setVisibility(View.GONE);
            // return;
        }
        // TODO 设置评论互动
    }
}