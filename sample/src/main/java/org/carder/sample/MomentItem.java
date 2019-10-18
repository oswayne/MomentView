package org.carder.sample;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import org.carder.view.MomentProvider;
import org.carder.view.adapter.AutoGridAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MomentItem implements MomentProvider {
    @Override
    public void setAvatar(ImageView imageView) {
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public String getContent() {
        return "朋友圈测试内容";
    }

    @Override
    public AutoGridAdapter getMediaViewAdapter() {
        List<Integer> data = new ArrayList<>();
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
        ArrayList<String> data = new ArrayList<>();
        data.add("小王");
        data.add("小张");
        data.add("小吴");
        data.add("小井");
        return data;
    }

    @Override
    public List<String> getComments() {
        return Collections.emptyList();
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
