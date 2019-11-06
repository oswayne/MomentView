package org.oswayne.view.moment;

public enum CommentModeEnum {
    DEF_COMMENT_MODE("默认无互动模式", 0),
    COMMENT_MODE("互动模式", 1);

    private String name;
    private int mode;

    CommentModeEnum(String name, int mode) {
        this.name = name;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public int getMode() {
        return mode;
    }
}
