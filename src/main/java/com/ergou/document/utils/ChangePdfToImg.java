package com.ergou.document.utils;

import com.ergou.document.enums.ResultCodeEnum;
import com.ergou.document.myxcption.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author: luoergou
 * @description: pdf转图片
 * @date: 2021-04-14 20:50
 */
@Slf4j
public class ChangePdfToImg {

    /**
     * pdf转图片
     *
     * @param PdfFilePath
     * @return
     */
    public static String pdf2Image(File PdfFilePath) throws IOException {
        PDDocument doc = PDDocument.load(PdfFilePath);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        String mkdirStr = File.separator + "opt" + File.separator + "temp" + File.separator + "tempimg" + File.separator;
        File file = new File(mkdirStr);
        if (!file.exists()) {
            file.mkdir();
        }
        for (int i = 0; i < pageCount; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 144); // Windows native DPI
            ImageIO.write(image, "jpg", new File(mkdirStr + i + ".jpg"));
        }
        doc.close();
        return mkdirStr;
    }
}
