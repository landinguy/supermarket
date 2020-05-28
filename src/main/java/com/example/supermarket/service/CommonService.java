package com.example.supermarket.service;

import com.example.supermarket.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CommonService {

    @Value("${upload.path}")
    private String path;
    @Value("${upload.url}")
    private String url;
    @Resource
    private HttpSession httpSession;

    public Integer getUserId() {
        return (Integer) Optional.ofNullable(httpSession.getAttribute(Consts.SEESION_UID)).orElse(null);
    }

    public String getUsername() {
        return (String) Optional.ofNullable(httpSession.getAttribute(Consts.SEESION_UNAME)).orElse(null);
    }

    public String getDateTime() {
        return LocalDateTime.now().withNano(0).toString().replace("T", " ");
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public Object upload(MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = getUUID() + "." + type;
        log.info("filename#{}", filename);
        String filePath = path + filename;
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return url + filename;
    }
}
