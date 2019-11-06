package org.oswayne.view.moment.provider;

public interface CommentProvider {
    String getId();

    String getUsername();

    CommentTypeEnum getType();

    String getReplyUsername();

    String getContent();

    enum CommentTypeEnum {

        DEF_COMMENT("默认评论", 0), REPLY_COMMENT("回复评论", 1);
        private String name;
        private int type;

        CommentTypeEnum(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }
    }
}
