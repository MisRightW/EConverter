package com.fengling.ecserver.util;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author fengling
 */
public class SlidesUtil {

    /**
     * PPT转PDF操作
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     */
    public static void pptToPdf(String sourceFile, String targetFile) {
        try {
            long old = System.currentTimeMillis();
            FileOutputStream os = new FileOutputStream(targetFile);
            Presentation ppt = new Presentation(sourceFile);//加载源文件数据
            ppt.save(os, com.aspose.slides.SaveFormat.Pdf);//设置转换文件类型并转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "D:\\test\\java.ppt";
        String target = "D:\\test\\java15.pdf";
        setLicence();
        pptToPdf(source, target);
    }

    private static void setLicence() {
        try {
            InputStream is = new FileInputStream("E:\\coder\\EConverter\\ec-server\\src\\main\\resources\\licence\\licence.xml");
            License license = new License();
            license.setLicense(is);
        }catch (Exception e) {
            System.out.println("");
        }

    }

}
