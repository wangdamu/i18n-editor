package com.mumu.i18n.editor.controller;

import com.mumu.i18n.editor.service.EditorService;
import com.mumu.i18n.editor.vo.FileChooseVO;
import com.mumu.i18n.editor.vo.FilesContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @RequestMapping(value = "/choose-dir", method = RequestMethod.POST)
    @ResponseBody
    public List<String> chooseDir(String dir){
        return editorService.listFiles(dir);
    }

    @RequestMapping(value = "/choose-files", method = RequestMethod.POST)
    @ResponseBody
    public FilesContentVO chooseFiles(FileChooseVO fileChooseVO){
        return editorService.readFiles(fileChooseVO);
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public String test(){
//        return "hello world";
//    }

//    @RequestMapping("/index")
//    public String index(){
//        return"/index";
//    }
}
