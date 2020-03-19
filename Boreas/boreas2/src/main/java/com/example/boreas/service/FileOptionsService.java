package com.example.boreas.service;

import com.example.boreas.model.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class FileOptionsService {

    @Autowired
    HandlerUserInfoService handlerUserInfoService;


    public boolean genterExcelFile(File newfile) {
        try {
            Workbook book = Workbook.getWorkbook(newfile);
            WritableWorkbook workbook = Workbook.createWorkbook(newfile, book);
            WritableSheet userSheet = workbook.getSheet(0);
            WritableSheet paperSheet = workbook.getSheet(1);
            WritableSheet proSheet = workbook.getSheet(2);
            WritableSheet comSheet = workbook.getSheet(3);
            //添加用户信息
            List<UserInfo> userInfos = handlerUserInfoService.queryUserInfoList();
            List<Clipue> clipues = handlerUserInfoService.queryClipue();
            if (userInfos != null && userInfos.size() > 0) {
                for (int i = 0; i < userInfos.size(); i++) {
                    UserInfo userInfo = userInfos.get(i);
                    Clipue clipue = null;
                    for (Clipue tempClipue : clipues) {
                        if (!userInfo.getUser_clipue().equals("") && tempClipue.getId() == Integer.valueOf(userInfo.getUser_clipue())) {
                            clipue = tempClipue;
                        }
                    }
                    Label idLabel = new Label(0, i + 2, String.valueOf(userInfo.getUser_id()));
                    Label nameLabel = new Label(1, i + 2, String.valueOf(userInfo.getUser_alias() != null ? userInfo.getUser_alias() : ""));
                    Label sexLabel = new Label(2, i + 2, String.valueOf(userInfo.getUser_sex() != null ? userInfo.getUser_sex() : ""));
                    Label birthdayLabel = new Label(3, i + 2, String.valueOf(userInfo.getUser_birthday()));
                    Label clipueLabel = new Label(4, i + 2, String.valueOf(clipue != null ? clipue.getClipue_name() : ""));
                    Label jobLabel = new Label(5, i + 2, String.valueOf(userInfo.getUser_jobtitle() != null ? userInfo.getUser_jobtitle() : ""));
                    Label phoneLabel = new Label(6, i + 2, String.valueOf(userInfo.getUser_telephone() != null ? userInfo.getUser_telephone() : ""));
                    Label proLabel = new Label(7, i + 2, String.valueOf(userInfo.getUser_research_pro_ids() != null && !userInfo.getUser_research_pro_ids().equals("") ? userInfo.getUser_research_pro_ids() : ""));
                    Label comLabel = new Label(8, i + 2, String.valueOf(userInfo.getUser_composition_ids() != null && !userInfo.getUser_composition_ids().equals("") ? userInfo.getUser_composition_ids() : ""));
                    Label paperLabel = new Label(9, i + 2, String.valueOf(userInfo.getUser_research_paper_ids() != null && !userInfo.getUser_research_paper_ids().equals("") ? userInfo.getUser_research_paper_ids() : ""));
                    userSheet.addCell(idLabel);
                    userSheet.addCell(nameLabel);
                    userSheet.addCell(sexLabel);
                    userSheet.addCell(birthdayLabel);
                    userSheet.addCell(clipueLabel);
                    userSheet.addCell(jobLabel);
                    userSheet.addCell(phoneLabel);
                    userSheet.addCell(proLabel);
                    userSheet.addCell(comLabel);
                    userSheet.addCell(paperLabel);
                }
            }
            //添加论文信息
            List<ResearchPaper> researchPapers = handlerUserInfoService.queryPaper();
            if (researchPapers != null && researchPapers.size() > 0) {
                for (int i = 0; i < researchPapers.size(); i++) {
                    ResearchPaper paper = researchPapers.get(i);
                    Label idLabel = new Label(0, i + 2, String.valueOf(paper.getId()));
                    Label nameLabel = new Label(1, i + 2, paper.getPaper_name() != null ? paper.getPaper_name() : "");
                    Label autherLabel = new Label(2, i + 2, paper.getPaper_author() != null ? paper.getPaper_author() : "");
                    Label otherLabel = new Label(3, i + 2, paper.getPaper_otherauthor() != null ? paper.getPaper_otherauthor() : "");
                    Label codeLabel = new Label(4, i + 2, paper.getPaper_periodical_code() != null ? paper.getPaper_periodical_code() : "");
                    Label categoryLabel = new Label(5, i + 2, paper.getPaper_category() != null ? paper.getPaper_category() : "");
                    Label publishLabel = new Label(6, i + 2, paper.getPaper_publish_date().toString() != null ? paper.getPaper_publish_date().toString() : "");
                    Label palmLabel = new Label(7, i + 2, paper.getPaper_bear_palm() != null ? paper.getPaper_bear_palm() : "");
                    paperSheet.addCell(idLabel);
                    paperSheet.addCell(nameLabel);
                    paperSheet.addCell(autherLabel);
                    paperSheet.addCell(otherLabel);
                    paperSheet.addCell(codeLabel);
                    paperSheet.addCell(categoryLabel);
                    paperSheet.addCell(publishLabel);
                    paperSheet.addCell(palmLabel);
                }
            }
            //添加项目信息
            List<ResearchProjectInfo> pros = handlerUserInfoService.queryPro();
            if (pros != null && pros.size() > 0) {
                for (int i = 0; i < pros.size(); i++) {
                    ResearchProjectInfo pro = pros.get(i);
                    Label idLabel = new Label(0, i + 2, String.valueOf(pro.getId()));
                    Label nameLabel = new Label(1, i + 2, pro.getPro_name() != null ? pro.getPro_name() : "");
                    Label levelLabel = new Label(2, i + 2, String.valueOf(pro.getPro_level()) != null ? String.valueOf(pro.getPro_level()) : "");
                    Label moneyLabel = new Label(3, i + 2, pro.getPro_money() != null ? pro.getPro_money() : "");
                    Label finishLabel = new Label(4, i + 2, pro.getPro_finish_date().toString() != null ? pro.getPro_finish_date().toString() : "");
                    Label statusLabel = new Label(5, i + 2, pro.getPro_current_status() != null ? pro.getPro_current_status() : "");
                    Label palmLabel = new Label(6, i + 2, pro.getPro_bear_palm() != null ? pro.getPro_bear_palm() : "");
                    proSheet.addCell(idLabel);
                    proSheet.addCell(nameLabel);
                    proSheet.addCell(levelLabel);
                    proSheet.addCell(moneyLabel);
                    proSheet.addCell(finishLabel);
                    proSheet.addCell(statusLabel);
                    proSheet.addCell(palmLabel);
                }
            }
            //添加著作信息
            List<ComPosition> comPositions = handlerUserInfoService.queryCom();
            if (comPositions != null && comPositions.size() > 0) {
                for (int i = 0; i < comPositions.size(); i++) {
                    ComPosition com = comPositions.get(i);
                    Label idLabel = new Label(0, i + 2, String.valueOf(com.getId()));
                    Label nameLabel = new Label(1, i + 2, com.getBook_name());
                    Label autherLabel = new Label(2, i + 2, com.getBook_auther());
                    Label otherLabel = new Label(3, i + 2, com.getBook_other_auther());
                    Label codeLabel = new Label(4, i + 2, com.getBook_periodical_code());
                    Label pressLabel = new Label(5, i + 2, com.getBook_press());
                    Label numLabel = new Label(6, i + 2, com.getBook_char_num());
                    Label moneyLabel = new Label(7, i + 2, com.getBook_money());
                    Label pressDateLabel = new Label(8, i + 2, com.getBook_press_date().toString());
                    Label palmDateLabel = new Label(9, i + 2, com.getBook_bear_palm());
                    comSheet.addCell(idLabel);
                    comSheet.addCell(nameLabel);
                    comSheet.addCell(autherLabel);
                    comSheet.addCell(otherLabel);
                    comSheet.addCell(codeLabel);
                    comSheet.addCell(pressLabel);
                    comSheet.addCell(numLabel);
                    comSheet.addCell(moneyLabel);
                    comSheet.addCell(pressDateLabel);
                    comSheet.addCell(palmDateLabel);
                }
            }
            workbook.write();
            workbook.close();
            //添加系别
            System.out.println("添加数据完成");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void writeBytes(File file, String newfilePath, HttpServletResponse response) throws Exception {
        FileInputStream fis = null;
        OutputStream os = null;
        try {

            File newFile = new File(newfilePath + "/TempUserInfo.xls");
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            this.copyFileUsingFileStreams(file, newFile);
            //添加数据
            boolean isSuccess = this.genterExcelFile(newFile);
            if (isSuccess) {
                response.setContentLength((int) newFile.length());
                System.out.println("length-------------" + newFile.length());
                fis = new FileInputStream(newFile);
                os = response.getOutputStream();
                byte[] b = new byte[1024];
                int length;
                while ((length = fis.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                os.flush();
            }
        } catch (IOException e) {
            System.out.println("writeBytes-------------" + e.getMessage());
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
        } catch (Exception e) {
            System.out.println("copyFileUsingFileStreams ++++++" + e.getMessage());
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
