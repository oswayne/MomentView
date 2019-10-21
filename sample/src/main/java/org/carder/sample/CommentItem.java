package org.carder.sample;

import org.carder.view.moment.CommentProvider;

public class CommentItem implements CommentProvider {
    @Override
    public String getId() {
        return "S1893541";
    }

    @Override
    public String getUsername() {
        return "小王";
    }

    @Override
    public CommentTypeEnum getType() {
        return CommentTypeEnum.REPLY_COMMENT;
    }

    @Override
    public String getReplyUsername() {
        return "小姚";
    }

    @Override
    public String getContent() {
        return "猪蹄子？";
    }
}
