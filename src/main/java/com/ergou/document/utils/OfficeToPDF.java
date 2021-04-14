package com.ergou.document.utils;


import cn.hutool.core.io.FileUtil;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;


/**
 * @author: luoergou
 * @description: word转pdf
 * @date: 2021-04-14 20:09
 */
@Slf4j
public class OfficeToPDF {
    /**
     * 记得修改自己的openoffice路径
     * 下面host_Str port_Str的一般都是默认 /opt/openoffice4 D:/OpenOffice 4
     */
    //public static String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
    public static String OpenOffice_HOME = "/opt/openoffice4";
    public static String host_Str = "39.108.123.162";
    public static String port_Str = "8081";

    /**
     * word文档转pdf文件
     *
     * @param sourceFile office文档绝对路径
     * @param destFile   pdf文件绝对路径
     * @return
     */
    public static int office2PDF(String sourceFile, String destFile) {
        log.info("word文档转pdf文件开始.....");
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            return -1; // 找不到源文件
        }
        log.info("如果目标路径不存在, 则新建该路径.....");
        // 如果目标路径不存在, 则新建该路径
        File outputFile = new File(destFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        try {
            log.info("开始转换");
            OpenOfficeConnection connection = initOpenOffice();
            if (!outputFile.exists()) {
                assert connection != null;
                return convertFile(inputFile, outputFile, connection);
            }
            // 关闭连接和服务
            assert connection != null;
            connection.disconnect();
            return -1;
        } catch (ConnectException e) {
            log.info("启动OpenOffice的服务异常:{}",ExceptionUtils.getStackTrace(e));
            OpenOfficeConnection connection = initOpenOffice();
            try {
                assert connection != null;
                return convertFile(inputFile, outputFile, connection);
            } catch (ConnectException e1) {
                log.info("启动OpenOffice的服务异常:{}",ExceptionUtils.getStackTrace(e1));
            }
        }
        return 1;
    }

    /**
     * 初始化启动openoffice服务
     *
     * @throws IOException
     */
    private static OpenOfficeConnection initOpenOffice() {
        try {
            // 启动OpenOffice的服务
            log.info("启动OpenOffice的服务.....");
            String separator = MyPathUtil.getSeparator();
            String command;
            if ("/".equals(separator)) {
                //linux系统
                command = OpenOffice_HOME
                        + "/program/soffice -headless -accept=\"socket,host="
                        + host_Str + ",port=" + port_Str + ";urp;\" -nofirststartwizard";
            } else {
                command = OpenOffice_HOME
                        + "\\program\\soffice.exe -headless -accept=\"socket,host="
                        + host_Str + ",port=" + port_Str + ";urp;\" -nofirststartwizard";
            }

            log.info("OpenOffice软件路径" + command);
            Runtime.getRuntime().exec(command);
            log.info("启动OpenOffice的服务成功");
            return new SocketOpenOfficeConnection(Integer.parseInt(port_Str));
        } catch (Exception e) {
            e.printStackTrace();
            log.info("###\n启动openoffice服务错误");
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String wordFile = "D:\\Users\\25356\\Desktop\\applic4sc.doc";
        String pdfFile = "D:\\Users\\25356\\Desktop\\applic4sc.pdf";
        File file = new File(pdfFile);
        office2PDF(wordFile, pdfFile);
        //ChangePdfToImg.pdf2Image(file);
        FileUtil.del(file);
    }

    private static int convertFile(File inputFile, File outputFile, OpenOfficeConnection connection) throws ConnectException {
        connection.connect();
        log.info("pdf开始转换，PDF输出：" + outputFile.getPath());
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);
        connection.disconnect();
        log.info("pdf转换成功，PDF输出：" + outputFile.getPath());
        return 0;
    }
}
