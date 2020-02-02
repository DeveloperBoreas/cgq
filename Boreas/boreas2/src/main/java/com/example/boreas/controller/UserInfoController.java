package com.example.boreas.controller;

import com.example.boreas.Constants;
import com.example.boreas.model.*;
import com.example.boreas.response.Response;
import com.example.boreas.service.HandlerUserInfoService;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserInfoController {

    @Autowired
    HandlerUserInfoService handlerUserInfoService;

    @GetMapping("/queryClipue")
    public Response queryClipue() {
        List<Clipue> clipues = handlerUserInfoService.queryClipue();
        if (clipues == null) {
            return Response.fail("没有录入系别").setretCode(Constants.FAILED);
        }
        return Response.ok(clipues).setretCode(Constants.SUCCESS);
    }

    @GetMapping("/queryPro")
    public Response queryPro() {
        List<ResearchProjectInfo> researchProjectInfos = handlerUserInfoService.queryPro();
        if (researchProjectInfos == null) {
            return Response.fail("没有录入项目信息").setretCode(Constants.FAILED_INPUT);
        }
        return Response.ok(researchProjectInfos).setretCode(Constants.SUCCESS);
    }

    @GetMapping("/queryPaper")
    public Response queryPaer() {
        List<ResearchPaper> researchPapers = handlerUserInfoService.queryPaper();
        if (researchPapers == null) {
            return Response.fail("没有录入论文信息").setretCode(Constants.FAILED_INPUT);
        }
        return Response.ok(researchPapers).setretCode(Constants.SUCCESS);
    }

    @GetMapping("/queryCom")
    public Response queryCom() {
        List<ComPosition> comPositions = handlerUserInfoService.queryCom();
        if (comPositions == null) {
            return Response.fail("没有录入学术著作").setretCode(Constants.FAILED_INPUT);
        }
        return Response.ok(comPositions).setretCode(Constants.SUCCESS);
    }

    @PostMapping("/insertClipue")
    public Response insertClipue(@RequestParam("clipueName") String clipueName) {
        System.out.print(clipueName);
        if (clipueName == null || clipueName.length() == 0) {
            return Response.fail("参数不能为空。").setretCode(Constants.PARAMS_NULL);
        }
        try {
            boolean isExist = false;
            List<Clipue> clipues = handlerUserInfoService.queryClipue();
            if (clipues != null) {
                for (Clipue clipue : clipues) {
                    if (clipue.getClipue_name().equals(clipueName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    return Response.fail("该系别已经存在！").setretCode(Constants.FAILED);
                }
                handlerUserInfoService.addClipue(clipueName);
            } else {
                handlerUserInfoService.addClipue(clipueName);
            }
            return Response.ok().setretCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("添加失败").setretCode(Constants.FAILED);
        }
    }

    @PostMapping("/insertPro")
    public Response insertPro(@RequestBody ResearchProjectInfo projectInfo) {
        System.out.println(projectInfo.toString());
        if (projectInfo == null) {
            return Response.fail("项目信息不能是空").setretCode(Constants.FAILED);
        }
        if (projectInfo.getId() == -1) {
            handlerUserInfoService.addPro(projectInfo);
            return Response.ok().setretCode(Constants.SUCCESS);
        }
        boolean isExist = false;
        List<ResearchProjectInfo> projectInfos = handlerUserInfoService.queryPro();
        if (projectInfos != null) {
            for (ResearchProjectInfo tempPro : projectInfos) {
                if (tempPro.getId() == projectInfo.getId()) {
                    handlerUserInfoService.addPro(projectInfo);
                    return Response.ok().setretCode(Constants.SUCCESS);
                }
            }
        } else {
            handlerUserInfoService.addPro(projectInfo);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/insertPaper")
    public Response insertPaper(@RequestBody ResearchPaper paper) {
        System.out.println(paper.toString());
        if (paper == null) {
            return Response.fail("论文信息不能为空").setretCode(Constants.FAILED);
        }
        boolean isExist = false;
        List<ResearchPaper> papers = handlerUserInfoService.queryPaper();
        if (papers != null) {
            for (ResearchPaper researchPaper : papers) {
                if (researchPaper.getPaper_name().equals(paper.getPaper_name())) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                return Response.fail("该论文已经存在！").setretCode(Constants.FAILED);
            }
            handlerUserInfoService.addPaper(paper);
        } else {
            handlerUserInfoService.addPaper(paper);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/insertComPositon")
    public Response insertCom(@RequestBody ComPosition comPosition) {
        System.out.println(comPosition.toString());
        if (comPosition == null) {
            return Response.fail("著作信息不能为空").setretCode(Constants.FAILED);
        }
        if (comPosition.getId() == -1) {
            handlerUserInfoService.addComPosition(comPosition);
            return Response.ok().setretCode(Constants.SUCCESS);
        }
        boolean isExist = false;
        List<ComPosition> comPositions = handlerUserInfoService.queryCom();
        if (comPositions != null) {
            for (ComPosition com : comPositions) {
                if (com.getBook_name().equals(comPosition.getBook_name())) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                return Response.fail("该著作已经存在！").setretCode(Constants.FAILED);
            }
            handlerUserInfoService.addComPosition(comPosition);
        } else {
            handlerUserInfoService.addComPosition(comPosition);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/insertUserInfo")
    public Response insertUserInfo(@RequestBody UserInfo info) {
        System.out.println(info.toString());
        if (info == null) {
            return Response.fail("没有教师信息").setretCode(Constants.FAILED);
        }
        boolean isExist = false;
        List<UserInfo> userInfos = handlerUserInfoService.queryUserInfoList();
        if (userInfos != null) {
            UserInfo tempUserInfo = null;
            for (UserInfo userInfo : userInfos) {
                if (userInfo.getUser_telephone().equals(info.getUser_telephone())) {
                    return Response.fail("手机号已经被注册").setretCode(Constants.FAILED);
                }
                if (userInfo.getUser_alias().equals(info.getUser_alias())) {
                    tempUserInfo = userInfo;
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                info.setUser_id(tempUserInfo.getUser_id());
                handlerUserInfoService.addUserInfo(info);
                return Response.ok().setretCode(Constants.SUCCESS);
            }
            handlerUserInfoService.addUserInfo(info);
        } else {
            handlerUserInfoService.addUserInfo(info);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/updateUserInfo")
    public Response updateUserInfo(@RequestBody UserInfo info) {
        try {
            List<UserInfo> userInfos = handlerUserInfoService.queryUserInfoList();
            if (userInfos != null) {
                for (UserInfo tempUserInfo : userInfos) {
                    if (tempUserInfo.getUser_name().equals(info.getUser_name()) && tempUserInfo.getUser_password().equals(info.getUser_password())) {
                        info.setUser_id(tempUserInfo.getUser_id());
                        handlerUserInfoService.addUserInfo(info);
                        return Response.ok().setretCode(Constants.SUCCESS);
                    }
                }
            } else {
                handlerUserInfoService.addUserInfo(info);
                return Response.ok().setretCode(Constants.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("更新失败").setretCode(Constants.SUCCESS);
        }
        return Response.fail("更新失败").setretCode(Constants.SUCCESS);
    }

    @PostMapping("/forgetUserInfo")
    public Response forgetUserInfo(@RequestParam("phone") String phone) {
        System.out.println(phone);
        List<UserInfo> userInfos = handlerUserInfoService.queryUserInfoList();
        for (UserInfo userInfo : userInfos) {
            if (userInfo.getUser_telephone().equals(phone)) {
                StringBuilder sb = new StringBuilder();
                sb.append(userInfo.getUser_name())
                        .append(",")
                        .append(userInfo.getUser_password());
                return Response.fail(sb.toString()).setretCode(Constants.SUCCESS);
            }
        }
        return Response.fail("当前手机号未注册").setretCode(Constants.FAILED);
    }

    @PostMapping("/deleteById")
    public Response deleteUserInfoById(@RequestParam("id") int userId) {
        System.out.println(userId);
        try {
            handlerUserInfoService.deleteUserById(userId);
            return Response.ok().setretCode(Constants.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.fail("删除失败").setretCode(Constants.FAILED);
    }
}
