package com.example.demo.persontest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeHtml {
    public static void main(String[] args) throws IOException {

        String fileName = "D:\\testaaa\\政策原文-杭州市科技型企业研发费用投入财政补助资金管理办法.html";

        Document doc = Jsoup.parse(new File(fileName), "utf-8");

        String text = doc.body().html();
        System.out.println(text);
//        Pattern p = Pattern.compile("(<[^>]+>)");
//        Matcher matcher = p.matcher(text);
//        String group = matcher.group(1);

        String s = regexTest2(text);
        System.out.println(text);

        String outputName = "D:\\testaaa\\政策原文-杭州市科技型企业研发费用投入财政补助资金管理办法.text";

        FileOutputStream fos = new FileOutputStream(outputName);

        try (OutputStreamWriter osw = new OutputStreamWriter(fos,
                StandardCharsets.UTF_8)) {
            osw.write(s);
        }
        fos.close();
    }

    @Test
    public void regexTest(){
        String content = " <p class=\"X1 X2\" style=\"margin-top:0.0pt;margin-bottom:10.5pt;background-color:#ffffff;white-space:pre-wrap;\"> <span id=\"_GoBack\"></span> <span class=\"X1 X2\" style=\"font-size:13.0pt;color:#ffffff;white-space:pre-wrap;\">杭州市科技型企业研发费用投入财政补助资金管理办法 </span> <span class=\"X1 X2\" style=\"font-family:'Microsoft YaHei UI';font-size:16.0pt;color:#333333;white-space:pre-wrap;\">【杭州市】关于印发《杭州市科技型企业研发费用投入财政补助资金管理办法》的通知 </span> </p>";

        Pattern p = Pattern.compile("(<p[^>]+>)");

        Matcher matcher = p.matcher(content);

        while (matcher.find()) {


            System.out.println(matcher.group(1));
        }
    }

//    @Test
    public static String regexTest2(String htmlStr){
//        String htmlStr =" <p class=\"X1 X2\" style=\"margin-top:0.0pt;margin-bottom:10.5pt;background-color:#ffffff;white-space:pre-wrap;\"> <span id=\"_GoBack\"></span> <span class=\"X1 X2\" style=\"font-size:13.0pt;color:#ffffff;white-space:pre-wrap;\">杭州市科技型企业研发费用投入财政补助资金管理办法 </span> <span class=\"X1 X2\" style=\"font-family:'Microsoft YaHei UI';font-size:16.0pt;color:#333333;white-space:pre-wrap;\">【杭州市】关于印发《杭州市科技型企业研发费用投入财政补助资金管理办法》的通知 </span> </p>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "(</?[^/?(br)|(p)][^><]*>)"; //定义HTML标签的正则表达式
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        System.out.println(htmlStr);
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);

        Matcher m_html = p_html.matcher(htmlStr);

        htmlStr = m_html.replaceAll(""); //过滤html标签
        htmlStr = htmlStr.replace(" ", "");
//        htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "<br>");
        htmlStr = htmlStr.replaceAll("\\n","<br>");
        htmlStr = htmlStr.replace("“", "");
        htmlStr = htmlStr.replace("”", "");
        htmlStr = htmlStr.replaceAll("　", "");
        htmlStr = htmlStr.replaceAll("(<p[^>]*>)","<p>");

        System.out.println(htmlStr.trim());
        return htmlStr;
    }
}
