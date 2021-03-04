package com.example.demo.persontest;

import fr.opensagres.poi.xwpf.converter.core.ImageManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.UUID;

/**
*@Description: word转成html
*@Author: xionglei
*@Date: 2021/3/4 9:53
*
*@return:
*/
@Component
public class WordToHtml {
    private static final Logger logger = LoggerFactory.getLogger(WordToHtml.class);

    //转换的方法
    public File convert(MultipartFile file) {
        //获得文件的名字
        String filename = file.getOriginalFilename();
        //获得文件的扩展名
        String suffix = filename.substring(filename.lastIndexOf("."));
        String newName = UUID.randomUUID().toString();
        // TODO 需要保存在一个新的位置
        // File =new File 表示目录的一个抽象,可以进一步用exists()和isDirectory()方法判断。

        File convFile = new File("D:/test/" + newName + suffix);
        FileOutputStream fos = null;
        try {
            //创建文件
            convFile.createNewFile();
            //FileOutputStream 是输出流 将文件输出到磁盘或者数据库中
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
        } catch (IOException ex) {
            logger.error("上传文件出错！", ex);
            return null;
        } finally {
            IOUtils.closeQuietly(fos);
        }

        // 输入文件名的所在文件夹
        // 加上反斜杠
        String parentDirectory = convFile.getParent();
        if (!parentDirectory.endsWith("\\")) {
            parentDirectory = parentDirectory + "\\";
        }


        if (filename.endsWith(".docx")) {
            return docxConvert(parentDirectory, convFile.getAbsolutePath(), newName);
        } else if (filename.endsWith(".doc")) {
            return docConvert(parentDirectory, convFile.getAbsolutePath(), newName);
        } else {
            logger.error("不支持的文件格式！");
            return null;
        }
    }


    public static File docxConvert(String parentDirectory, String filename, String newName) {
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(filename));
            XHTMLOptions options = XHTMLOptions.create().setImageManager(new ImageManager(new File(parentDirectory), "images\\"+newName)).indent(4);
            FileOutputStream out = new FileOutputStream(new File(parentDirectory + newName + ".html"));
            XHTMLConverter.getInstance().convert(document, out, options);
            File file = new File(parentDirectory + newName + ".html");
            String parent = file.getParent();
            System.out.println(parent);
            return file;
        } catch (IOException ex) {
            logger.error("word转化出错！", ex);
            return null;
        }

    }


    public static File docConvert(String parentDirectory, String filename, String newName) {
        try {
            HWPFDocument document = new HWPFDocument(new FileInputStream(new File(filename)));
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .newDocument());

            // converter默认对图片不作处理，需要手动下载图片并嵌入到html中
            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                @Override
                public String savePicture(byte[] bytes, PictureType pictureType, String s, float v, float v1) {
                    String imageFilename = parentDirectory + "images\\"+newName;
                    String identity = UUID.randomUUID().toString();
                    File imageFile = new File(imageFilename, identity + s);
                    imageFile.getParentFile().mkdirs();
                    InputStream in = null;
                    FileOutputStream out = null;

                    try {
                        in = new ByteArrayInputStream(bytes);
                        out = new FileOutputStream(imageFile);
                        IOUtils.copy(in, out);

                    } catch (IOException ex) {
                        logger.error("word转化出错！", ex);
                    } finally {
                        if (in != null) {
                            IOUtils.closeQuietly(in);
                        }

                        if (out != null) {
                            IOUtils.closeQuietly(out);
                        }

                    }
                    return imageFile.getName();
                }
            });

            wordToHtmlConverter.processDocument(document);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(out);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            out.close();

            String result = new String(out.toByteArray());
            FileWriter writer = new FileWriter(parentDirectory + newName + ".html");
            writer.write(result);
            writer.close();
        } catch (IOException | TransformerException | ParserConfigurationException ex) {
            logger.error("word转化出错！", ex);
        }
        return new File(parentDirectory + newName + ".html");
    }

    /**
     * 将上传的Word文档转化成HTML字符串
     *
     * @param attachfile
     * @return
     */
    public String convertToHtml(MultipartFile attachfile) {
        String wordContent = "";

        // 将Word文件转换为html
        File file = convert(attachfile);
        // 读取html文件
        if (file != null) {
            return "文件转换成功";
        }
        return "文件转换失败";
    }

    @Test
    public void testoutputhtml(){
//        docConvert("D:\\testaaa\\","D:\\testaaa\\bcc.doc","bcc");
//        docxConvert("D:\\testaaa\\","D:\\testaaa\\aaa.docx","aaa");
        docxConvert("D:\\testaaa\\","D:\\testaaa\\政策原文-杭州市科技型企业研发费用投入财政补助资金管理办法.docx","政策原文-杭州市科技型企业研发费用投入财政补助资金管理办法");
    }

}