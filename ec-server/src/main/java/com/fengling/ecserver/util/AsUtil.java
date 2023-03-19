package com.fengling.ecserver.util;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Aspose 系列jar破解工具  ---  针对21.11版本
 *
 * @author fengling
 * <p>
 * 破解方法：
 * 1、去下载对应版本的jar，比如"D:\\aspose\\aspose-pdf-21.11.jar"
 * 2、项目里引入依赖：javassist
 * 3、通过该工具getAsPDF2就能得到破解的class文件，如主函数调用
 * 4、jar -xvf aspose-pdf-21.11.jar  解压jar，替换com目录，并且删掉.RSA和.SF
 * 5、jar -cfM0 aspose-pdf-21.11.jar ./  重新把当前解压后的文件打成新的jar包
 * 6、引入项目: mvn install:install-file -Dfile=D:\aspose\new\aspose-pdf-21.11.jar -DgroupId=com.aspose -DartifactId=aspose-pdf -Dversion=21.11.3 -Dpackaging=jar
 *
 * : pdf -- mvn install:install-file -Dfile=D:\aspose\pdf\target\aspose-pdf-21.11.jar -DgroupId=com.aspose -DartifactId=aspose-pdf -Dversion=21.11.12 -Dpackaging=jar
 * : word -- mvn install:install-file -Dfile=D:\aspose\word\target\aspose-words-21.11-jdk17.jar -DgroupId=com.aspose -DartifactId=aspose-word -Dversion=21.11.12 -Dpackaging=jar
 * : cells -- mvn install:install-file -Dfile=D:\aspose\cells\target\aspose-cells-21.11.jar -DgroupId=com.aspose -DartifactId=aspose-cells -Dversion=21.11.12 -Dpackaging=jar
 * : slides -- mvn install:install-file -Dfile=D:\aspose\slides\target\aspose-slides-21.11-jdk16.jar -DgroupId=com.aspose -DartifactId=aspose-slides -Dversion=21.11.12 -Dpackaging=jar
 */
public class AsUtil {

    public static void main(String[] args) {
        // source : 下载原始jar的路径  target：破解后jar存储目录路径
//        getAsPDF("D:\\aspose\\aspose-pdf-21.11.jar", "D:\\aspose\\new\\");
//        getAsPDF2("D:\\aspose\\aspose-pdf-21.11.jar", "D:\\aspose\\new2\\");

        // word
//        getAsWord("D:\\aspose\\word\\aspose-words-21.11-jdk17.jar", "D:\\aspose\\word\\new\\");

        // Cells
//        getAsCells("D:\\aspose\\cells\\aspose-cells-21.11.jar", "D:\\aspose\\cells\\new\\");

        // slides
        getAsSlides("D:\\aspose\\slides\\aspose-slides-21.11-jdk16.jar", "D:\\aspose\\slides\\new\\");
    }

    public static void getAsSlides(String source, String target) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(source);
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.slides.internal.oh.public");
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ps.length == 3 && ctMethod.getName().equals("do")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    ctMethod.setBody("{}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(target);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }

    public static void getAsCells(String source, String target) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(source);
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.cells.License");
            //从class对象中解析获取所有方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                //获取方法获取参数类型
                CtClass[] ps = ctMethod.getParameterTypes();
                //筛选同名方法，入参是Document
                if (ps.length == 1 && ctMethod.getName().equals("a") && ps[0].getName().equals("org.w3c.dom.Document")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{a = this;com.aspose.cells.zblc.a();}");
                }
            }
            //这一步就是将破译完的代码放在目标文件夹
            zzZJJClass.writeFile(target);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }

    public static void getAsWord(String source, String target) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(source);
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.words.zzXDb");
            //从class对象中解析获取指定的方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods("zzY0J");
            //遍历重载的方法
            for (CtMethod ctMethod : methodA) {
                CtClass[] ps = ctMethod.getParameterTypes();
                if (ctMethod.getName().equals("zzY0J")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{this.zzZ3l = new java.util.Date(Long.MAX_VALUE);this.zzWSL = com.aspose.words.zzYeQ.zzXgr;zzWiV = this;}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(target);

            //获取指定的class文件对象
            CtClass zzZJJClassB = ClassPool.getDefault().getCtClass("com.aspose.words.zzYKk");
            //从class对象中解析获取指定的方法
            CtMethod methodB = zzZJJClassB.getDeclaredMethod("zzWy3");
            //替换指定方法的方法体
            methodB.setBody("{return 256;}");
            //这一步就是将破译完的代码放在桌面上
            zzZJJClassB.writeFile(target);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }


    public static void getAsPDF(String source, String target) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(source);
            //获取指定的class文件对象
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.l9f");
            //从class对象中解析获取所有方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                //获取方法获取参数类型
                CtClass[] ps = ctMethod.getParameterTypes();
                //筛选同名方法，入参是Document
                if (ps.length == 1 && ctMethod.getName().equals("lI") && ps[0].getName().equals("java.io.InputStream")) {
                    System.out.println("ps[0].getName==" + ps[0].getName());
                    //替换指定方法的方法体
                    ctMethod.setBody("{this.l0if = com.aspose.pdf.l10if.lf;com.aspose.pdf.internal.imaging.internal.p71.Helper.help1();lI(this);}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(target);

        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }


    public static void getAsPDF2(String source, String target) {
        try {
            //这一步是完整的jar包路径,选择自己解压的jar目录
            ClassPool.getDefault().insertClassPath(source);
            CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
            //从class对象中解析获取所有方法
            CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
            for (CtMethod ctMethod : methodA) {
                //获取方法获取参数类型
                CtClass[] ps = ctMethod.getParameterTypes();
                //筛选同名方法，入参是Document
                if (ps.length == 0 && (ctMethod.getName().equals("lj") || ctMethod.getName().equals("lt"))) {
                    ctMethod.setBody("{return true;}");
                }
                if (ps.length == 1 && (ctMethod.getName().equals("lI")) && ps[0].getName().equals("com.aspose.pdf.internal.l10k.ly")) {
                    ctMethod.setBody("{return true;}");
                }
            }
            //这一步就是将破译完的代码放在桌面上
            zzZJJClass.writeFile(target);
        } catch (Exception e) {
            System.out.println("错误==" + e);
        }
    }

}
