package com.example.boreas.controller;


import com.example.boreas.Constants;
import com.example.boreas.model.UserInfo;
import com.example.boreas.model.recebean.LoginReceBean;
import com.example.boreas.response.Response;
import com.example.boreas.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/test")
    public Response test() {
        return Response.ok("我就看不懂了").setretCode(1);
    }


    @PostMapping(value = "/login")
    public Response login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPsd) {
        System.out.println(userName);
        System.out.println(userPsd);
        if (userName != null && userPsd != null) {
            if (userName.equals(Constants.ADMIN) && userPsd.equals(Constants.ADMIN)) {
                UserInfo userInfo = loginService.login(userName, userPsd);
                return Response.ok(loginService.handlerUserInfo(userInfo,
                        null,
                        null,
                        null,
                        null))
                        .setretCode(Constants.SUCCESS);
            } else {
                UserInfo userInfo = loginService.login(userName, userPsd);
                if (userInfo == null) {
                    return Response.fail("当前账号未注册，请联系管理员录入。").setretCode(Constants.FAILED);
                }
                return Response.ok(loginService.handlerUserInfo(userInfo,
                        loginService.queryClipueByUserInfo(userInfo),
                        loginService.queryResearchProByUserInfo(userInfo),
                        loginService.queryResearchPaperByUserInfo(userInfo),
                        loginService.queryCompositionByUserInfo(userInfo)))
                        .setretCode(Constants.SUCCESS);
            }
        } else {
            return Response.fail("用户名和密码不能为空").setretCode(Constants.FAILED);
        }
    }

    @GetMapping(value = "/queryUserList")
    public Response queryUserList() {
        List<LoginReceBean> userList = loginService.findAll();
        if (userList == null || userList.size() < 1) {
            return Response.fail("无教师信息").setretCode(Constants.FAILED);
        }
        return Response.ok(userList).setretCode(0);
    }
}
