package com.fengling.ecserver.util;

import com.aspose.cells.License;
import com.aspose.cells.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author fengling
 */
public class CellUtil {

    public static void excelToPdf(String sourceFile, String targetFile) {
        setLicence();
        try {
            long old = System.currentTimeMillis();
            FileOutputStream os = new FileOutputStream(targetFile);
            Workbook excel = new Workbook(sourceFile);//加载源文件数据
            excel.save(os, com.aspose.cells.SaveFormat.PDF);//设置转换文件类型并转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "D:\\test\\java.xlsx";
        String target = "D:\\test\\java5.pdf";
        setLicence();
        excelToPdf(source, target);
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
