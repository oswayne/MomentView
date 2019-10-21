package org.carder.view.moment;

import android.widget.ImageView;

import org.carder.view.adapter.AutoGridAdapter;

import java.util.List;

public interface MomentProvider {
    void setAvatar(ImageView imageView);

    String getContent();

    String getTitle();

    AutoGridAdapter getMediaViewAdapter();

    String getLocate();

    String getTime();

    String getType();

    List<String> getPraises();

    List<CommentProvider> getComments();
}
