package com.mumu.i18n.editor.service;

import com.mumu.i18n.editor.vo.FileChooseVO;
import com.mumu.i18n.editor.vo.FilesContentVO;

import java.util.List;

/**
 * 编辑器service
 */
public interface EditorService {

    /**
     * 列出所有的文件
     * @param dir
     * @return
     */
    List<String> listFiles(String dir);

    /**
     * 读取文件内容
     * @param fileChooseVO
     * @return
     */
    FilesContentVO readFiles(FileChooseVO fileChooseVO);
}
