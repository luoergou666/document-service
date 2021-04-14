package com.ergou.document.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ZipUtil;
import com.ergou.document.enums.ResultCodeEnum;
import com.ergou.document.myxcption.MyException;
import com.ergou.document.utils.ChangePdfToImg;
import com.ergou.document.utils.OfficeToPDF;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author: luoergou
 * @description:
 * @date: 2021-04-14 21:31
 */
@Service
@Slf4j
public class DocumentService {


    public void pdfTpImage(MultipartFile multipartFile, HttpServletResponse httpServletResponse) {
        log.info(multipartFile.getOriginalFilename());
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        if (originalFilename.contains("docx")) {
            throw new MyException(ResultCodeEnum.DATA_EXCEPTION);
        }
        String mkdirStr = File.separator + "opt" + File.separator + "temp" + File.separator;
        File mkdir = new File(mkdirStr);
        if (!mkdir.exists()) {
            mkdir.mkdir();
        }
        //保存到临时文件夹
        File wordFile = new File(mkdirStr + System.currentTimeMillis() + originalFilename);
        File pdfFile = new File(mkdirStr + System.currentTimeMillis() + originalFilename.replace("doc", "pdf"));
        String zipPath = mkdirStr + System.currentTimeMillis() + ".zip";
        FileInputStream is = null;
        BufferedInputStream bs = null;
        File zip = null;
        String imagesPath = "";
        try (OutputStream out = new FileOutputStream(wordFile);
             ServletOutputStream os = httpServletResponse.getOutputStream();) {
            byte[] ss = multipartFile.getBytes();
            for (byte s : ss) {
                out.write(s);
            }
            OfficeToPDF.office2PDF(wordFile.getPath(), pdfFile.getPath());
            imagesPath = ChangePdfToImg.pdf2Image(pdfFile);
            zip = ZipUtil.zip(imagesPath, zipPath);
            //设置Headers
            httpServletResponse.setHeader("Content-Type", "application/octet-stream");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + zip.getName());
            is = new FileInputStream(zip);
            bs = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bs.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            out.flush();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            IoUtil.close(bs);
            IoUtil.close(is);
            //FileUtil.del(imagesPath);
            //FileUtil.del(zip);
            //FileUtil.del(wordFile);
            //FileUtil.del(pdfFile);
        }
    }
}
