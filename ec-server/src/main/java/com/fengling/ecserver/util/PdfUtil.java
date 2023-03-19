package com.fengling.ecserver.util;

import java.io.FileOutputStream;

public class PdfUtil {

    /**
     * PDF转Word操作
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     */
    public static void pdf2doc(String sourceFile, String targetFile) {
        try {
            long old = System.currentTimeMillis();
            FileOutputStream os = new FileOutputStream(targetFile);
            com.aspose.pdf.Document doc = new com.aspose.pdf.Document(sourceFile);//加载源文件数据
            doc.save(os, com.aspose.pdf.SaveFormat.DocX);//设置转换文件类型并转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "D:\\test\\java.pdf";
        String target = "D:\\test\\java10.docx";
        pdf2doc(source, target);
    }

}
