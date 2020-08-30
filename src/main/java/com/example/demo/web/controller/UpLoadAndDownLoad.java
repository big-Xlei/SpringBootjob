package com.example.demo.web.controller;

import com.example.demo.bean.Person;
import com.example.demo.bean.Result;
import com.example.demo.utiles.ResultMappingImpl;
import com.example.demo.utiles.UsersException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.UserException;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传和下载
 */
@RestController()
@RequestMapping("/upload")
@Slf4j
@Api("图片上传相关接口")
public class UpLoadAndDownLoad {

    @GetMapping(value = "uploadImage")
    @ApiOperation("用户上传图片")
    public Result uploadImage(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return new ResultMappingImpl().error("文件不能为空");
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        log.info("文件上传成功文件名为：" + fileName);

        //获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传后缀名为：" + suffixName);

        //文件上传后的路径
        String filePath = "D://temp-rainy//";

        //解决中文问题，linux下中文路径，图片显示问题  或者是：新文件名

        fileName = UUID.randomUUID() + suffixName;


        File desc = new File(filePath + fileName);
        log.info("新文件路径为=" + desc);
        try {
            file.transferTo(desc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "/temp-rainy/" + fileName;
        model.addAttribute("filename", filename);

        return ResultMappingImpl.success("图片上传成功");

    }

    @PostMapping(value = "login")
    @ApiOperation("测试用户登入系统")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄", paramType = "query")
    })*/
    public Result Testpost(@RequestBody @Validated Person sutdent) {

        log.error("走了这里==============================================="+sutdent.name+sutdent.age);
        if (!(sutdent.name.equals("大熊") && sutdent.age == 18)) {
                throw new UsersException("该学生不存在");
            }


            return ResultMappingImpl.success(sutdent);
    }
}
