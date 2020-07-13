package com.example.demo.web.controller;

import com.example.demo.bean.Result;
import com.example.demo.utile.Impl.ResultMappingImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 *
 * */
@Controller("uploadDownload")
public class UpLoadAndDownLoad {
    private static final Logger logger = LoggerFactory.getLogger("UpLoadAndDownLoad");
    @Value("${uploadDir}")
    private String uploadDir;

    @PostMapping(value = "/uploadImage")
    public Result uploadImage(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request){
       if(file.isEmpty()){
           return new ResultMappingImpl().error("文件不能为空");
       }
       //获取文件名
        String fileName = file.getOriginalFilename();
       logger.info("文件上传成功文件名为："+fileName);

       //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传后缀名为："+ suffixName);

        //文件上传后的路径
        String filePath = "D//temp-rainy//";

        //解决中文问题，linux下中文路径，图片显示问题  或者是：新文件名

         fileName = UUID.randomUUID()+suffixName;

        File desc = new File(filePath + fileName);
        try {
            file.transferTo(desc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename",filename);

        return  new ResultMappingImpl().success("图片上传成功");

    }
}
