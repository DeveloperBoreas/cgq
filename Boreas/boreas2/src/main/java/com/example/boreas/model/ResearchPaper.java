package com.example.boreas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 科研论文信息
 */
@Entity
@Table(name = "research_paper_info")
public class ResearchPaper {
    @Id
    @GeneratedValue
    private int id;
    private String paper_name;             //论文题目
    private String paper_author;           //第一作者
    private String paper_otherauthor;      //其他作者
    private String paper_periodical_code;  //刊物编码
    private String paper_category;         //类别
    private Date paper_publish_date;     //发表日期
    private String paper_bear_palm;        //获奖情况

    public int getId() {
        return id;
    }

    public ResearchPaper setId(int id) {
        this.id = id;
        return this;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public ResearchPaper setPaper_name(String paper_name) {
        this.paper_name = paper_name;
        return this;
    }

    public String getPaper_author() {
        return paper_author;
    }

    public ResearchPaper setPaper_author(String paper_author) {
        this.paper_author = paper_author;
        return this;
    }

    public String getPaper_otherauthor() {
        return paper_otherauthor;
    }

    public ResearchPaper setPaper_otherauthor(String paper_otherauthor) {
        this.paper_otherauthor = paper_otherauthor;
        return this;
    }

    public String getPaper_periodical_code() {
        return paper_periodical_code;
    }

    public ResearchPaper setPaper_periodical_code(String paper_periodical_code) {
        this.paper_periodical_code = paper_periodical_code;
        return this;
    }

    public String getPaper_category() {
        return paper_category;
    }

    public ResearchPaper setPaper_category(String paper_category) {
        this.paper_category = paper_category;
        return this;
    }

    public Date getPaper_publish_date() {
        return paper_publish_date;
    }

    public ResearchPaper setPaper_publish_date(Date paper_publish_date) {
        this.paper_publish_date = paper_publish_date;
        return this;
    }

    public String getPaper_bear_palm() {
        return paper_bear_palm;
    }

    public ResearchPaper setPaper_bear_palm(String paper_bear_palm) {
        this.paper_bear_palm = paper_bear_palm;
        return this;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "id=" + id +
                ", paper_name='" + paper_name + '\'' +
                ", paper_author='" + paper_author + '\'' +
                ", paper_otherauthor='" + paper_otherauthor + '\'' +
                ", paper_periodical_code='" + paper_periodical_code + '\'' +
                ", paper_category='" + paper_category + '\'' +
                ", paper_publish_date=" + paper_publish_date +
                ", paper_bear_palm='" + paper_bear_palm + '\'' +
                '}';
    }
}
