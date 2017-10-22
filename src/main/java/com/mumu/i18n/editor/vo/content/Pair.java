package com.mumu.i18n.editor.vo.content;

/**
 * 条目: key value形式的值
 */
public class Pair implements IEntry{
    private final String key;
    private final String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getText() {
        return key + "=" + value;
    }

    @Override
    public int getType() {
        return 1;
    }
}
