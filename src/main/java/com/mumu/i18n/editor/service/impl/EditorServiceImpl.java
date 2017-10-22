package com.mumu.i18n.editor.service.impl;

import com.mumu.i18n.editor.service.EditorService;
import com.mumu.i18n.editor.service.I18NFileReaderService;
import com.mumu.i18n.editor.vo.FileChooseVO;
import com.mumu.i18n.editor.vo.FileContentVO;
import com.mumu.i18n.editor.vo.FilesContentVO;
import com.mumu.i18n.editor.vo.content.IEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 编辑器service实现
 */
@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private I18NFileReaderService i18NFileReaderService;

    @Override
    public List<String> listFiles(String dir) {
        return Arrays.asList(new File(dir).listFiles(file -> file.isFile())).stream().map(file -> file.getName()).collect(Collectors.toList());
    }

    @Override
    public FilesContentVO readFiles(FileChooseVO fileChooseVO) {
        List<FileContentVO> contentVOS = new ArrayList<>();
        fileChooseVO.getFiles().forEach(file->{
            if(file.isChoosed()){
                List<IEntry> list = i18NFileReaderService.readI18NFile(new File(fileChooseVO.getDir(), file.getName()).getAbsolutePath(), true);
                FileContentVO fc = new FileContentVO(file.getName(), list);
                contentVOS.add(fc);
            }
        });

        contentVOS.sort((a, b)->{
            if(a.getFileName().endsWith("_zh.properties")){
                return -1;
            }
            if(b.getFileName().endsWith("_zh.properties")){
                return 1;
            }
            return b.getPairs().size() - a.getPairs().size();
        });

        FilesContentVO ret = new FilesContentVO();
        ret.setContents(contentVOS);
        return ret;
    }
}
