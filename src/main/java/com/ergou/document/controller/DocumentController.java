package com.ergou.document.controller;

import com.ergou.document.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: luoergou
 * @description: 文档转换
 * @date: 2021-04-14 21:17
 */
@RestController
@RequestMapping(path = "/document")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping(value = "/pdfTpImage")
    public void pdfTpImage(@RequestParam(name = "file") MultipartFile file, HttpServletResponse httpServletResponse){
        documentService.pdfTpImage(file,httpServletResponse);
    }
}
