package com.mumu.i18n.editor.vo;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

/**
 * 文件选择VO
 */
public class FileChooseVO {
    private String dir;
    private List<FileChoice> files;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<FileChoice> getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = JSON.parseArray(files, FileChoice.class);
    }

    public static class FileChoice{
        private String name;
        private boolean choosed;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChoosed() {
            return choosed;
        }

        public void setChoosed(boolean choosed) {
            this.choosed = choosed;
        }
    }
}
