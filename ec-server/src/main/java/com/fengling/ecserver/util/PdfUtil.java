package com.fengling.ecserver.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.devices.JpegDevice;
import com.aspose.pdf.devices.Resolution;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public static void split(String source, String target) {

    }

    /**
     * 将pdf转图片
     *
     * @param inputStream pdf源文件流
     * @param imgFilePath 转成一张图片文件全路径 例如 "D:\\home\\qq.png"
     */
    public static void pdfToImage(InputStream inputStream, String imgFilePath) {
        try {
            log.info("convert pdf2jpg begin");
            long old = System.currentTimeMillis();
            Document pdfDocument = new Document(inputStream);
            //分辨率
            Resolution resolution = new Resolution(130);
            JpegDevice jpegDevice = new JpegDevice(resolution);
            List<BufferedImage> imageList = new ArrayList<BufferedImage>();
            List<File> fileList = new ArrayList<>();
            for (int index = 1; index <= pdfDocument.getPages().size(); index++) {
                File file = File.createTempFile("tempFile", "png");
                FileOutputStream fileOS = new FileOutputStream(file);
                jpegDevice.process(pdfDocument.getPages().get_Item(index), fileOS);
                fileOS.close();
                imageList.add(ImageIO.read(file));
                fileList.add(file);
            }
            //临时文件删除
            BufferedImage mergeImage = mergeImage(false, imageList);
            ImageIO.write(mergeImage, "png", new File(imgFilePath));
            long now = System.currentTimeMillis();
            log.info("convert pdf2jpg completed, elapsed ：" + ((now - old) / 1000.0) + "秒");
            //删除临时文件
            for (File f : fileList) {
                if (f.exists()) {
                    f.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("convert pdf2jpg error:" + e);
        }

    }

    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         待合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, List<BufferedImage> imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;
        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        // 获取总长、总宽、最长、最宽
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            allw += img.getWidth();

            if (imgs.size() != i + 1) {
                allh += img.getHeight() + 5;
            } else {
                allh += img.getHeight();
            }


            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }
        Graphics2D g2 = (Graphics2D) destImage.getGraphics();
        g2.setBackground(Color.LIGHT_GRAY);
        g2.clearRect(0, 0, allw, allh);
        g2.setPaint(Color.RED);

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.size(); i++) {
            BufferedImage img = imgs.get(i);
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            // 逐行扫描图像中各个像素的RGB到数组中
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1);
            if (isHorizontal) {
                // 水平方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1);
            } else {
                // 垂直方向合并
                // 设置上半部分或左半部分的RGB
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1);
            }
            wx += w1;
            wy += h1 + 5;
        }


        return destImage;
    }

    /**
     * pfd转图片
     *
     * @param pdf     源文件全路径
     * @param outPath 转后的文件夹路径
     */
    public static void pdfToImage(String pdf, String outPath) {

        try {
            long old = System.currentTimeMillis();
            log.info("convert pdf2jpg begin");
            Document pdfDocument = new Document(pdf);
            //图片宽度：800
            //图片高度：100
            // 分辨率 960
            //Quality [0-100] 最大100
            //例： new JpegDevice(800, 1000, resolution, 90);
            Resolution resolution = new Resolution(960);
            JpegDevice jpegDevice = new JpegDevice(resolution);
            for (int index = 1; index <= pdfDocument.getPages().size(); index++) {
                // 输出路径
                File file = new File(outPath + "/" + index + ".jpg");
                FileOutputStream fileOs = new FileOutputStream(file);
                jpegDevice.process(pdfDocument.getPages().get_Item(index), fileOs);
                fileOs.close();
            }

            long now = System.currentTimeMillis();
            log.info("convert pdf2jpg completed, elapsed ：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("convert pdf2jpg error:" + e);
        }


    }


    public static void main(String[] args) {
        String source = "D:\\test\\test.pdf";
        String target = "D:\\test\\split\\test.pdf";
//        pdf2doc(source, target);
//        pdfToImage(source, target);
        convert(source, target);
    }

    /**
     * @Param source 源文件
     * @Param target 转换后文件
     * @Description 将PDF转为A4格式
     * @Date: 2021/4/25
     **/
    private static void convert(String source, String target) {
        try {
            PdfReader pdfReader = new PdfReader(source);
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(target));
            doc.open();
            PdfContentByte cb = writer.getDirectContent();
            for(int i = 1; i <= pdfReader.getNumberOfPages(); i++){
                PdfImportedPage page = writer.getImportedPage(pdfReader, i);
                float width = page.getWidth();
                float height = page.getHeight();
                if(height > width) {
                    //横向
                    doc.setPageSize(PageSize.A4);
                    doc.newPage();
                    //计算比例
                    float widthScale = getWidthScale(width);
                    float heightScale = getHeightScale(height);
                    //addTemplate方法中有6个float类型的参数，是通过二维图像仿射变换得到的
                    //cb.addTemplate(page, new AffineTransform(widthScale, 0, 0, heightScale,0,0));
                    //二维图像仿射变换:https://www.cnblogs.com/v2m_/archive/2013/05/09/3070187.html
                    cb.addTemplate(page, widthScale, 0, 0, heightScale,0,0);
                } else {
                    //纵向
                    doc.setPageSize(new com.itextpdf.text.Rectangle(PageSize.A4.getHeight(), PageSize.A4.getWidth()));
                    doc.newPage();
                    float widthScale = getWidthScale(height);
                    float heightScale = getHeightScale(width);
                    cb.addTemplate(page, widthScale, 0, 0, heightScale,0,0);
                }
            }
            doc.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static float getWidthScale(float width) {
        float scale = PageSize.A4.getWidth() / width;
        return scale;
    }

    private static float getHeightScale(float height) {
        float scale = PageSize.A4.getHeight() / height;
        return scale;
    }

}
