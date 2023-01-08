package me.looouiiis.controller;

import me.looouiiis.pojo.JsonContentReturn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传与下载
 */
@RestController
@RequestMapping("/common")
public class Common {
    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public JsonContentReturn upload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return null;
    }
}
