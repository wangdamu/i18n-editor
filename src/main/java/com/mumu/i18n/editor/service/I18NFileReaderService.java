package com.mumu.i18n.editor.service;

import com.mumu.i18n.editor.vo.content.IEntry;

import java.util.List;

/**
 * 国际化资源文件读取service
 */
public interface I18NFileReaderService {

    /**
     * 读取国际化文件
     * @param filePath
     * @param unicodeToStr
     * @return
     */
    List<IEntry> readI18NFile(String filePath, boolean unicodeToStr);

}
