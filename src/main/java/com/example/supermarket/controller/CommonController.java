package com.example.supermarket.controller;

import com.example.supermarket.service.CommonService;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CommonController {
    @Resource
    private CommonService commonService;

    @PostMapping("upload")
    public Result upload(MultipartFile file) {
        log.info("上传文件,filename#{}", file.getOriginalFilename());
        Result.ResultBuilder builder = Result.builder();
        try {
            builder.data(commonService.upload(file));
        } catch (Exception e) {
            log.error("上传文件失败", e);
            builder.code(-1).msg("文件上传失败").build();
        }
        return builder.build();
    }

}