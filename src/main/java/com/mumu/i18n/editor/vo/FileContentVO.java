package com.mumu.i18n.editor.vo;

import com.mumu.i18n.editor.vo.content.IEntry;
import com.mumu.i18n.editor.vo.content.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件内容VO
 */
public class FileContentVO {

    private String fileName;

    private List<IEntry> content;

    private Map<String, String> pairs;

    public FileContentVO() {
    }

    public FileContentVO(String fileName, List<IEntry> content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<IEntry> getContent() {
        return content;
    }

    public void setContent(List<IEntry> content) {
        this.content = content;
    }

    public Map<String, String> getPairs() {
        if(pairs == null && content != null){
            Map<String, String> map = new HashMap<>();
            content.stream().filter(t->t instanceof Pair).forEach(t->{
                Pair pair = (Pair)t;
                map.put(pair.getKey(), pair.getValue());
            });
            pairs = map;
        }
        return pairs;
    }

    public void setPairs(Map<String, String> pairs) {
        this.pairs = pairs;
    }

}
