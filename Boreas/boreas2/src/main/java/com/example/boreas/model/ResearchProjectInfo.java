package com.example.boreas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "research_pro_info")
public class ResearchProjectInfo {
    @Id
    @GeneratedValue
    private int id;

    private String  pro_name;               //项目名称
    private int     pro_level;              //项目级别
    private String  pro_money;              //项目经费
    private Date    pro_finish_date;        //完成日期
    private String     pro_current_status;     //当前状态
    private String  pro_bear_palm;          //获奖情况

    public int getId() {
        return id;
    }

    public ResearchProjectInfo setId(int id) {
        this.id = id;
        return this;
    }

    public String getPro_name() {
        return pro_name;
    }

    public ResearchProjectInfo setPro_name(String pro_name) {
        this.pro_name = pro_name;
        return this;
    }

    public int getPro_level() {
        return pro_level;
    }

    public ResearchProjectInfo setPro_level(int pro_level) {
        this.pro_level = pro_level;
        return this;
    }

    public String getPro_money() {
        return pro_money;
    }

    public ResearchProjectInfo setPro_money(String pro_money) {
        this.pro_money = pro_money;
        return this;
    }

    public Date getPro_finish_date() {
        return pro_finish_date;
    }

    public ResearchProjectInfo setPro_finish_date(Date pro_finish_date) {
        this.pro_finish_date = pro_finish_date;
        return this;
    }

    public String getPro_current_status() {
        return pro_current_status;
    }

    public ResearchProjectInfo setPro_current_status(String pro_current_status) {
        this.pro_current_status = pro_current_status;
        return this;
    }

    public String getPro_bear_palm() {
        return pro_bear_palm;
    }

    public ResearchProjectInfo setPro_bear_palm(String pro_bear_palm) {
        this.pro_bear_palm = pro_bear_palm;
        return this;
    }

    @Override
    public String toString() {
        return "ResearchProjectInfo{" +
                "id=" + id +
                ", pro_name='" + pro_name + '\'' +
                ", pro_level=" + pro_level +
                ", pro_money='" + pro_money + '\'' +
                ", pro_finish_date=" + pro_finish_date +
                ", pro_current_status='" + pro_current_status + '\'' +
                ", pro_bear_palm='" + pro_bear_palm + '\'' +
                '}';
    }
}
