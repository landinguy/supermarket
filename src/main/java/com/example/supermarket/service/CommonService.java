package com.example.supermarket.service;

import com.example.supermarket.util.Consts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author landing guy
 * @date 2020/4/18
 */
@Service
public class CommonService {
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

}
