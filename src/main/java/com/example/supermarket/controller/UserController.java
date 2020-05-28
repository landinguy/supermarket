package com.example.supermarket.controller;

import com.example.supermarket.controller.model.UserReq;
import com.example.supermarket.entity.User;
import com.example.supermarket.service.UserService;
import com.example.supermarket.util.Consts;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("login")
    public Result login(String username, String password) {
        try {
            return userService.checkAndLogin(username, password);
        } catch (Exception e) {
            return Result.builder().code(-1).msg("系统异常").build();
        }
    }

    @GetMapping("logout")
    public Result logout(HttpSession session) {
        if (session != null) {
            session.removeAttribute(Consts.SEESION_UNAME);
            session.removeAttribute(Consts.SEESION_UID);
        }
        return Result.builder().build();
    }

    @PostMapping("addAccount")
    public Result add(@RequestBody User user) {
        log.info("添加或修改用户,user#{}", user);
        Result.ResultBuilder builder = Result.builder();
        try {
            return userService.add(user);
        } catch (Exception e) {
            log.error("添加或修改用户失败,user#{}", user);
            builder.code(-1).msg("添加或修改用户失败");
        }
        return builder.build();
    }

    @GetMapping("updatePwd")
    public Result updatePwd(String oldPassword, String newPassword) {
        log.info("修改密码,oldPassword#{},newPassword#{}", oldPassword, newPassword);
        try {
            return userService.updatePassword(oldPassword, newPassword);
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return Result.builder().code(-1).msg("修改密码失败").build();
        }
    }

    @GetMapping("updatePassword")
    public Result updatePassword(Integer id, String password) {
        log.info("修改密码,id#{},password#{}", id, password);
        Result.ResultBuilder builder = Result.builder();
        try {
            userService.updatePassword(id, password);
        } catch (Exception e) {
            log.error("修改密码失败", e);
            builder.code(-1).msg("修改密码失败").build();
        }
        return builder.build();
    }

    @PostMapping("getAccount")
    public Result getAccount(@RequestBody UserReq req) {
        log.info("查询用户,req#{}", req);
        Result.ResultBuilder builder = Result.builder();
        try {
            builder.data(userService.select(req));
        } catch (Exception e) {
            log.error("查询用户失败", e);
            builder.code(-1).msg("查询用户失败").build();
        }
        return builder.build();
    }

    @RequestMapping("deleteAccount")
    public Result deleteAccount(Integer id) {
        try {
            log.info("delete account,id#{}", id);
            return userService.delete(id);
        } catch (Exception e) {
            log.error("delete account error#{}", e);
            return Result.builder().code(-1).msg("删除用户失败").build();
        }
    }

}
