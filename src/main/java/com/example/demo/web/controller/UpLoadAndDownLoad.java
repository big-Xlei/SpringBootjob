package com.example.demo.web.controller;

import com.example.demo.bean.Result;
import com.example.demo.utiles.ResultMappingImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 *
 * */
@RestController()
@RequestMapping("/upload")
@Api("图片上传相关接口")
public class UpLoadAndDownLoad {
    private static final Logger logger = LoggerFactory.getLogger("UpLoadAndDownLoad");

    @PostMapping(value = "uploadImage")
    @ApiOperation("用户上传图片")
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
        String filePath = "D://temp-rainy//";

        //解决中文问题，linux下中文路径，图片显示问题  或者是：新文件名

         fileName = UUID.randomUUID()+suffixName;


        File desc = new File(filePath + fileName);
        logger.info("新文件路径为="+desc);
        try {
            file.transferTo(desc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename",filename);

        return ResultMappingImpl.success("图片上传成功");

    }
}
