package com.fengling.ecserver.util;

import com.aspose.words.License;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author fengling
 */
public class WordUtil {

    public static void main(String[] args) {
        String target = "D:\\test\\java2.pdf";
        String source = "D:\\test\\java3.docx";

        setLicence();

        word2pdf(source, target);
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

    public static void word2pdf(String sourceFile, String targetFile) {
        try {
            long old = System.currentTimeMillis();
            FileOutputStream os = new FileOutputStream(targetFile);
            com.aspose.words.Document doc = new com.aspose.words.Document(sourceFile);
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
