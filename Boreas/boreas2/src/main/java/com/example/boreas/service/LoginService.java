package com.example.boreas.service;


import com.example.boreas.model.*;
import com.example.boreas.model.recebean.LoginReceBean;
import com.example.boreas.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    ClipueRepository clipueRepository;
    @Autowired
    ResearchProjectInfoRepository researchProjectInfoRepository;
    @Autowired
    ResearchPaperRepository researchPaperRepository;
    @Autowired
    ComPositionRepository comPositionRepository;

    public UserInfo login(String name, String psd) {
        UserInfo userInfo = userInfoRepository.findByUser_nameAndUser_password(name, psd);
        return userInfo;
    }

    public List<LoginReceBean> findAll() {
        ArrayList<LoginReceBean> userList = new ArrayList<>();
        List<UserInfo> list = userInfoRepository.findAll();
        if (list == null || list.size() < 1) {
            return null;
        }
        for (UserInfo userInfo : list) {
            LoginReceBean loginReceBean = this.handlerUserInfo(userInfo,
                    this.queryClipueByUserInfo(userInfo),
                    this.queryResearchProByUserInfo(userInfo),
                    this.queryResearchPaperByUserInfo(userInfo),
                    this.queryCompositionByUserInfo(userInfo));
            userList.add(loginReceBean);
        }
        return userList;
    }

    public ArrayList<LoginReceBean.Clipue> queryClipueByUserInfo(UserInfo userInfo) {
        ArrayList<LoginReceBean.Clipue> clipues = new ArrayList<>();
        String user_clipue = userInfo.getUser_clipue();
        System.out.println(user_clipue);
        try {
            if (user_clipue != null && user_clipue.length() > 0) {
                String[] split = user_clipue.split(",");
                if (split != null && split.length > 0) {
                    List<Integer> clipueIds = new ArrayList<>();
                    for (String id : split) {
                        clipueIds.add(Integer.parseInt(id));
                    }
                    List<Clipue> clipueList = clipueRepository.findAllById(clipueIds);
                    if (clipueList != null && clipueList.size() > 0) {
                        for (Clipue clipue : clipueList) {
                            clipues.add(new LoginReceBean.Clipue(clipue.getId(), clipue.getClipue_name()));
                        }
                    }
                }
            }
            return clipues;
        } catch (Exception e) {
            e.printStackTrace();
            return clipues;
        }
    }

    public ArrayList<LoginReceBean.ResearchPro> queryResearchProByUserInfo(UserInfo userInfo) {
        ArrayList<LoginReceBean.ResearchPro> researchPros = new ArrayList<>();
        String user_research_pro_ids = userInfo.getUser_research_pro_ids();
        System.out.println(user_research_pro_ids);
        try {
            if (user_research_pro_ids != null && user_research_pro_ids.length() > 0) {
                String[] split = user_research_pro_ids.split(",");
                if (split != null && split.length > 0) {
                    List<Integer> proIds = new ArrayList<>();
                    for (String id : split) {
                        proIds.add(Integer.parseInt(id));
                    }
                    List<ResearchProjectInfo> proList = researchProjectInfoRepository.findAllById(proIds);
                    if (proList != null && proList.size() > 0) {
                        for (ResearchProjectInfo projectInfo : proList) {
                            researchPros.add(new LoginReceBean.ResearchPro(
                                    projectInfo.getId(),
                                    projectInfo.getPro_name(),
                                    projectInfo.getPro_level(),
                                    projectInfo.getPro_money(),
                                    projectInfo.getPro_finish_date().toString(),
                                    projectInfo.getPro_current_status(),
                                    projectInfo.getPro_bear_palm()));
                        }
                    }
                }
            }
            return researchPros;
        } catch (Exception e) {
            e.printStackTrace();
            return researchPros;
        }
    }

    public ArrayList<LoginReceBean.ResearchPaper> queryResearchPaperByUserInfo(UserInfo userInfo) {
        ArrayList<LoginReceBean.ResearchPaper> researchPapers = new ArrayList<>();
        String paper_ids = userInfo.getUser_research_paper_ids();
        System.out.println(paper_ids);
        try {
            if (paper_ids != null && paper_ids.length() > 0) {
                String[] split = paper_ids.split(",");
                if (split != null && split.length > 0) {
                    List<Integer> paperIds = new ArrayList<>();
                    for (String id : split) {
                        paperIds.add(Integer.parseInt(id));
                    }
                    List<ResearchPaper> proList = researchPaperRepository.findAllById(paperIds);
                    if (proList != null && proList.size() > 0) {
                        for (ResearchPaper paper : proList) {
                            researchPapers.add(new LoginReceBean.ResearchPaper(
                                    paper.getId(),
                                    paper.getPaper_name(),
                                    paper.getPaper_author(),
                                    paper.getPaper_otherauthor(),
                                    paper.getPaper_periodical_code(),
                                    paper.getPaper_category(),
                                    paper.getPaper_publish_date().toString(),
                                    paper.getPaper_bear_palm()));
                        }
                    }
                }
            }
            return researchPapers;
        } catch (Exception e) {
            e.printStackTrace();
            return researchPapers;
        }
    }

    public ArrayList<LoginReceBean.Composition> queryCompositionByUserInfo(UserInfo userInfo) {
        ArrayList<LoginReceBean.Composition> compositions = new ArrayList<>();
        String composition_ids = userInfo.getUser_composition_ids();
        System.out.println(composition_ids);
        try {
            if (composition_ids != null && composition_ids.length() > 0) {
                String[] split = composition_ids.split(",");
                if (split != null && split.length > 0) {
                    List<Integer> compositionIds = new ArrayList<>();
                    for (String id : split) {
                        compositionIds.add(Integer.parseInt(id));
                    }
                    List<ComPosition> proList = comPositionRepository.findAllById(compositionIds);
                    if (proList != null && proList.size() > 0) {
                        for (ComPosition comPosition : proList) {
                            compositions.add(new LoginReceBean.Composition(
                                    comPosition.getId(),
                                    comPosition.getBook_name(),
                                    comPosition.getBook_auther(),
                                    comPosition.getBook_other_auther(),
                                    comPosition.getBook_periodical_code(),
                                    comPosition.getBook_press(),
                                    comPosition.getBook_press_date().toString(),
                                    comPosition.getBook_char_num(),
                                    comPosition.getBook_money(),
                                    comPosition.getBook_bear_palm()));
                        }
                    }
                }
            }
            return compositions;
        } catch (Exception e) {
            e.printStackTrace();
            return compositions;
        }
    }


    public LoginReceBean handlerUserInfo(UserInfo userInfo,
                                         ArrayList<LoginReceBean.Clipue> clipues,
                                         ArrayList<LoginReceBean.ResearchPro> researchPros,
                                         ArrayList<LoginReceBean.ResearchPaper> researchPapers,
                                         ArrayList<LoginReceBean.Composition> compositions) {
        LoginReceBean loginReceBean = new LoginReceBean();
        loginReceBean.setUser_alias(userInfo.getUser_alias());
        loginReceBean.setUser_address(userInfo.getUser_address());
        loginReceBean.setUser_birthday(userInfo.getUser_birthday().toString());
        loginReceBean.setUser_id(userInfo.getUser_id());
        loginReceBean.setUser_permission(userInfo.getUser_permission());
        loginReceBean.setUser_jobtitle(userInfo.getUser_jobtitle());
        loginReceBean.setUser_sex(userInfo.getUser_sex());
        loginReceBean.setUser_telephone(userInfo.getUser_telephone());
        loginReceBean.setUser_clipues(clipues);
        loginReceBean.setResearchPros(researchPros);
        loginReceBean.setResearchPapers(researchPapers);
        loginReceBean.setCompositions(compositions);
        loginReceBean.setName(userInfo.getUser_name());
        loginReceBean.setPsd(userInfo.getUser_password());
        loginReceBean.setHeadIcon(userInfo.getUser_head_icon());
        return loginReceBean;
    }

}
