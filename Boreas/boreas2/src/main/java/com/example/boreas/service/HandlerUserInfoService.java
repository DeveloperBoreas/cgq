package com.example.boreas.service;

import com.example.boreas.model.*;
import com.example.boreas.model.recebean.LoginReceBean;
import com.example.boreas.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HandlerUserInfoService {
    @Autowired
    ClipueRepository clipueRepository;
    @Autowired
    ResearchProjectInfoRepository researchProjectInfoRepository;
    @Autowired
    ResearchPaperRepository researchPaperRepository;
    @Autowired
    ComPositionRepository comPositionRepository;
    @Autowired
    UserInfoRepository userInfoRepository;

    public List<Clipue> queryClipue() {
        List<Clipue> all = clipueRepository.findAll();
        if (all != null && all.size() > 0) {
            return all;
        }
        return null;
    }

    public List<ResearchProjectInfo> queryPro() {
        List<ResearchProjectInfo> all = researchProjectInfoRepository.findAll();
        if (all != null && all.size() > 0) {
            return all;
        }
        return null;
    }

    public List<ResearchPaper> queryPaper() {
        List<ResearchPaper> all = researchPaperRepository.findAll();
        if (all != null && all.size() > 0) {
            return all;
        }
        return null;
    }

    public List<ComPosition> queryCom() {
        List<ComPosition> all = comPositionRepository.findAll();
        if (all != null && all.size() > 0) {
            return all;
        }
        return null;
    }

    public void addClipue(String clipueName) {
        clipueRepository.saveAndFlush(new Clipue().setClipue_name(clipueName));
    }

    public void addPro(ResearchProjectInfo proInfo) {
        researchProjectInfoRepository.saveAndFlush(proInfo);
    }

    public void addPaper(ResearchPaper paper) {
        researchPaperRepository.saveAndFlush(paper);
    }

    public void addComPosition(ComPosition comPosition) {
        comPositionRepository.saveAndFlush(comPosition);
    }

    public List<UserInfo> queryUserInfoList() {
        List<UserInfo> all = userInfoRepository.findAll();
        if (all != null && all.size() > 0) {
            return all;
        }
        return null;
    }

    public UserInfo queryUserInfoById(int id) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        UserInfo info = userInfo.get();
        return info;
    }

    public void addUserInfo(UserInfo info) {
        userInfoRepository.saveAndFlush(info);
    }

    public void deleteUserById(int id) {
        userInfoRepository.deleteById(id);
    }
}
