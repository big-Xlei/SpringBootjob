package com.example.demo.persontest;

import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.xdocreport.core.io.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


@Slf4j
public class Word2HtmlUtil {

    public static void main(String[] args) {
//        File file = new File("D:\\testaaa\\aaa.docx");

        //File转MultipartFile
        File file=new File("D:\\testaaa\\aaa.docx");
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(input);
            MockMultipartFile mockMultipartFile = new MockMultipartFile("file", file.getName(), "text/plain", bytes);
            String s = Word2007ToHtml(mockMultipartFile);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        MultipartFile multipartFile =new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

    }
    public static String Word2007ToHtml(MultipartFile file) throws IOException {

        if (file.isEmpty() || file.getSize() <= 0) {
            log.error("Sorry File does not Exists!");
            return null;
        } else {
            if (file.getOriginalFilename().endsWith(".docx") || file.getOriginalFilename().endsWith(".DOCX")) {

                // 1) 加载word文档生成 XWPFDocument对象
                InputStream in = file.getInputStream();
                XWPFDocument document = new XWPFDocument(in);

                // 也可以使用字符数组流获取解析的内容
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                XHTMLConverter.getInstance().convert(document, baos, null);
                String content = baos.toString();
                baos.close();
                return content;
            } else {
                log.error("Enter only MS Office 2007+ files");
                return null;
            }
        }
    }

    public static String Word2003ToHtml(MultipartFile file)
            throws IOException, ParserConfigurationException, TransformerException {

        if (file.isEmpty() || file.getSize() <= 0) {
            log.error("Sorry File does not Exists!");
            return null;
        } else {
            if (file.getOriginalFilename().endsWith(".doc") || file.getOriginalFilename().endsWith(".DOC")) {
                InputStream input = file.getInputStream();
                HWPFDocument wordDocument = new HWPFDocument(input);
                WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                        DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

                // 解析word文档
                wordToHtmlConverter.processDocument(wordDocument);
                Document htmlDocument = wordToHtmlConverter.getDocument();

                // 也可以使用字符数组流获取解析的内容
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DOMSource domSource = new DOMSource(htmlDocument);
                StreamResult streamResult = new StreamResult(baos);

                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer serializer = factory.newTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");
                serializer.setOutputProperty(OutputKeys.METHOD, "html");
                serializer.transform(domSource, streamResult);

                // 也可以使用字符数组流获取解析的内容
                String content = new String(baos.toByteArray());
                baos.close();
                return content;
            } else {
                log.error("Enter only MS Office 2003 files");
                return null;
            }
        }

    }

}
