package com.example.boreas.controller;

import com.example.boreas.Constants;
import com.example.boreas.model.*;
import com.example.boreas.response.Response;
import com.example.boreas.service.HandlerUserInfoService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserInfoController {

    @Value("E:/fileUpload/")
    private String uploadPath;
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
        boolean isExist = false;
        List<ResearchProjectInfo> projectInfos = handlerUserInfoService.queryPro();
        if (projectInfos != null) {
            for (ResearchProjectInfo tempPro : projectInfos) {
                if (tempPro.getId() == projectInfo.getId()) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                return Response.fail("该项目已经存在！").setretCode(Constants.FAILED);
            }
        } else {
            handlerUserInfoService.addPro(projectInfo);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/insertPros")
    public Response insertPros(@RequestBody ArrayList<ResearchProjectInfo> projectInfos) {
        if (projectInfos == null || projectInfos.size() == 0) {
            return Response.fail("导入项目信息不能是空").setretCode(Constants.FAILED);
        }
        System.out.println(projectInfos.size());
        List<ResearchProjectInfo> tempProjectInfos = handlerUserInfoService.queryPro();
        int line = 0;
        for (ResearchProjectInfo tempPro : projectInfos) { // 上传的数据

            boolean isEmpty = true;
            for (ResearchProjectInfo inTempPro : tempProjectInfos) { //本地数据
                if (tempPro.getPro_name().equals(inTempPro.getPro_name())) {//比较项目名是否相同，相同则视为一个项目,不同则进行保存
                    tempPro.setId(inTempPro.getId());
                    handlerUserInfoService.addPro(tempPro);
                    isEmpty = false;
                    line++;
                    continue;
                }
            }
            if (isEmpty) {
                handlerUserInfoService.addPro(tempPro);
                line++;
            }
        }
        if (line > 0) {
            System.out.println("添加的数据 数量：" + line);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }


    @PostMapping("/updatePro")
    public Response updatePro(@RequestBody ResearchProjectInfo projectInfo) {
        System.out.println(projectInfo.toString());
        if (projectInfo == null) {
            return Response.fail("更新项目信息不能为空").setretCode(Constants.FAILED);
        }
        if (projectInfo.getId() == -1) {
            return Response.fail("当前更新项目ID不能为空").setretCode(Constants.FAILED);
        }
        List<ResearchProjectInfo> researchProjectInfos = handlerUserInfoService.queryPro();
        for (ResearchProjectInfo info : researchProjectInfos) {
            if (info.getId() == projectInfo.getId()) {
                projectInfo.setId(info.getId());
                handlerUserInfoService.addPro(projectInfo);
                return Response.ok().setretCode(Constants.SUCCESS);
            }
        }
        return Response.fail("更新项目信息失败").setretCode(Constants.FAILED);

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
                if (researchPaper.getId() == paper.getId()) {
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

    @PostMapping("/insertPapers")
    public Response insertPapers(@RequestBody ArrayList<ResearchPaper> ResearchPapers) {
        if (ResearchPapers == null || ResearchPapers.size() == 0) {
            return Response.fail("导入论文信息不能是空").setretCode(Constants.FAILED);
        }
        System.out.println(ResearchPapers.size());
        List<ResearchPaper> tempPapers = handlerUserInfoService.queryPaper();
        int line = 0;
        for (ResearchPaper tempPaper : ResearchPapers) { // 上传的数据

            boolean isEmpty = true;
            for (ResearchPaper inTempPaper : tempPapers) { //本地数据
                if (tempPaper.getPaper_name().equals(inTempPaper.getPaper_name())) {//比较项目名是否相同，相同则视为一个项目,不同则进行保存
                    tempPaper.setId(inTempPaper.getId());
                    handlerUserInfoService.addPaper(tempPaper);
                    isEmpty = false;
                    line++;
                    continue;
                }
            }
            if (isEmpty) {
                handlerUserInfoService.addPaper(tempPaper);
                line++;
            }
        }
        if (line > 0) {
            System.out.println("添加的数据 数量：" + line);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/updatePaper")
    public Response updatePaper(@RequestBody ResearchPaper paper) {
        System.out.println(paper.toString());
        if (paper == null) {
            return Response.fail("更新论文信息不能为空").setretCode(Constants.FAILED);
        }
        if (paper.getId() == -1) {
            return Response.fail("当前论文信息ID不能为空").setretCode(Constants.FAILED);
        }
        List<ResearchPaper> researchPapers = handlerUserInfoService.queryPaper();
        for (ResearchPaper tempPaper : researchPapers) {
            if (tempPaper.getId() == paper.getId()) {
                paper.setId(tempPaper.getId());
                handlerUserInfoService.addPaper(paper);
                return Response.ok().setretCode(Constants.SUCCESS);
            }
        }
        return Response.fail("更新论文信息失败").setretCode(Constants.FAILED);
    }

    @PostMapping("/insertComPositon")
    public Response insertCom(@RequestBody ComPosition comPosition) {
        System.out.println(comPosition.toString());
        if (comPosition == null) {
            return Response.fail("著作信息不能为空").setretCode(Constants.FAILED);
        }
        boolean isExist = false;
        List<ComPosition> comPositions = handlerUserInfoService.queryCom();
        if (comPositions != null) {
            for (ComPosition com : comPositions) {
                if (com.getId() == comPosition.getId()) {
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

    @PostMapping("/insertComPositons")
    public Response insertComPositons(@RequestBody ArrayList<ComPosition> comPositions) {
        if (comPositions == null || comPositions.size() == 0) {
            return Response.fail("导入著作信息不能是空").setretCode(Constants.FAILED);
        }
        System.out.println(comPositions.size());
        List<ComPosition> tempComPositions = handlerUserInfoService.queryCom();
        int line = 0;
        for (ComPosition tempComPosition : comPositions) { // 上传的数据

            boolean isEmpty = true;
            for (ComPosition inTempComPosition : tempComPositions) { //本地数据
                if (tempComPosition.getBook_name().equals(inTempComPosition.getBook_name())) {//比较项目名是否相同，相同则视为一个项目,不同则进行保存
                    tempComPosition.setId(inTempComPosition.getId());
                    handlerUserInfoService.addComPosition(tempComPosition);
                    isEmpty = false;
                    line++;
                    continue;
                }
            }
            if (isEmpty) {
                handlerUserInfoService.addComPosition(tempComPosition);
                line++;
            }
        }
        if (line > 0) {
            System.out.println("添加的数据 数量：" + line);
        }
        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @PostMapping("/updateComPositon")
    public Response updateComPositon(@RequestBody ComPosition comPosition) {
        System.out.println(comPosition.toString());
        if (comPosition == null) {
            return Response.fail("更新著作信息不能为空").setretCode(Constants.FAILED);
        }
        if (comPosition.getId() == -1) {
            return Response.fail("当前著作信息ID不能为空").setretCode(Constants.FAILED);
        }
        List<ComPosition> comPositions = handlerUserInfoService.queryCom();
        for (ComPosition tempComPosition : comPositions) {
            if (tempComPosition.getId() == comPosition.getId()) {
                comPosition.setId(tempComPosition.getId());
                handlerUserInfoService.addComPosition(comPosition);
                return Response.ok().setretCode(Constants.SUCCESS);
            }
        }
        return Response.fail("更新著作信息失败").setretCode(Constants.FAILED);
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

    @PostMapping("/upload")
    public Response upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Response.fail("上传失败，请选择文件").setretCode(Constants.FAILED);
        }
        String fileName = file.getOriginalFilename();
        String[] split = fileName.split("@@@@@");
        fileName = split[0];
        String userId = split[1];
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        String path = uploadPath + fileName;
        File dest = new File(path);
        if (dest.exists()) {
            return Response.fail("文件已经存在").setretCode(Constants.FAILED);
        }
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest); //保存文件
            System.out.print("保存文件路径" + path + "\n");
            UserInfo userInfo = handlerUserInfoService.queryUserInfoById(Integer.parseInt(userId));
            if (userInfo == null) {
                return Response.fail("上传失败，没有查询到该用户").setretCode(Constants.FAILED);
            }
            userInfo.setUser_head_icon("images/" + fileName);
            handlerUserInfoService.addUserInfo(userInfo);

        } catch (IOException e) {
            return Response.fail("上传失败").setretCode(Constants.FAILED);
        }

        return Response.ok().setretCode(Constants.SUCCESS);
    }

    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        //1：复制一个模板
        //2：往附件上添加数据
        //3：通过数据流的方式进行文件传输

        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
        try
        {
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
