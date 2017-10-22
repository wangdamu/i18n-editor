package com.mumu.i18n.editor.vo.content;

/**
 * 条目-注释
 */
public class Comment implements IEntry{
    private final String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String getText() {
        return comment;
    }

    @Override
    public int getType() {
        return 0;
    }
}
