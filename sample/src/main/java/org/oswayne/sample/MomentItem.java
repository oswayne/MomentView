package org.oswayne.sample;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import org.carder.sample.R;
import org.oswayne.view.adapter.AutoGridAdapter;
import org.oswayne.view.moment.provider.CommentProvider;
import org.oswayne.view.moment.provider.MomentProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MomentItem implements MomentProvider {

    private List<String> praisesList = new ArrayList<>();
    private List<CommentProvider> commentProviderList = new ArrayList<>();

    public MomentItem() {
        praisesList.add("小王");
        praisesList.add("小张");
        praisesList.add("小吴");
        praisesList.add("小姚");

        commentProviderList.add(new CommentItem());
    }

    @Override
    public void setAvatar(ImageView imageView) {
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public String getContent() {
        return "朋友圈测试内容";
    }

    @Override
    public AutoGridAdapter<?> getMediaViewAdapter() {
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);

        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);
        data.add(R.mipmap.ic_launcher);

        data.add(R.mipmap.ic_launcher);

        return new AutoGridAdapter<Integer>(data) {
            @NotNull
            @Override
            public View createView(@NotNull Context context, int i) {
                return new ImageView(context);
            }

            @Override
            public void initView(@NotNull View view, int i) {
                ((ImageView) view).setImageResource(getData().get(i));
            }
        };
    }

    @Override
    public List<String> getPraises() {
        return praisesList;
    }

    @Override
    public List<CommentProvider> getComments() {
        return commentProviderList;
    }

    @Override
    public String getType() {
        return "来自网易云分享";
    }

    @Override
    public String getLocate() {
        return "美国 芝加哥";
    }

    @Override
    public String getTime() {
        return "2019-10-18";
    }

    @Override
    public String getTitle() {
        return "测试标题";
    }
}
