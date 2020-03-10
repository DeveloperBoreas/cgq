package com.example.boreas.service;

import com.example.boreas.model.ComPosition;
import com.example.boreas.model.ResearchPaper;
import com.example.boreas.model.ResearchProjectInfo;
import com.example.boreas.model.UserInfo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class FileOptionsService {

    @Autowired
    HandlerUserInfoService handlerUserInfoService;

    public void genterExcelFile(File newfile) throws Exception {
        WritableWorkbook workbook = Workbook.createWorkbook(newfile);
        WritableSheet userSheet = workbook.getSheet(0);
        WritableSheet paperSheet = workbook.getSheet(1);
        WritableSheet proSheet = workbook.getSheet(2);
        WritableSheet comSheet = workbook.getSheet(3);
        //添加用户信息
        List<UserInfo> userInfos = handlerUserInfoService.queryUserInfoList();
        if (userInfos != null && userInfos.size() > 0) {
            for (int i = 0; i < userInfos.size(); i++) {
                UserInfo userInfo = userInfos.get(i);
                Label label = new Label(0,i+3,String.valueOf(userInfo.getUser_id()));
                userSheet.addCell(label);
            }
        }
        //添加论文信息
        List<ResearchPaper> researchPapers = handlerUserInfoService.queryPaper();
        if (researchPapers != null && researchPapers.size() > 0) {

        }
        //添加项目信息
        List<ResearchProjectInfo> pros = handlerUserInfoService.queryPro();
        if (pros != null && pros.size() > 0) {

        }
        //添加著作信息
        List<ComPosition> comPositions = handlerUserInfoService.queryCom();
        if (comPositions != null && comPositions.size() > 0) {

        }
        //添加系别
    }


    public void writeBytes(File file, String newfilePath, OutputStream os) throws Exception {
        FileInputStream fis = null;
        try {
            File newFile = new File(newfilePath);
            this.copyFileUsingFileStreams(file, newFile);
            //添加数据
            this.genterExcelFile(newFile);
            if (!file.exists()) {
                throw new FileNotFoundException(newFile.getName());
            }
            fis = new FileInputStream(newFile);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static void copyFileUsingFileStreams(File source, File dest) throws IOException {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }


    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
