package com.boreas.modle;

public class UserInfo {

    private String user_alias;
    private String user_name;               //用户名
    private String user_password;           //密码
    private String user_sex;                //性别
    private String user_birthday;           //生日
    private String user_clipue;             //所属系
    private String user_jobtitle;           //职称
    private String user_address;            //地址
    private String user_telephone;          //手机号码
    private String user_research_pro_ids;   //科研项目信息
    private String user_research_paper_ids; //科研论文信息
    private String user_composition_ids;    //学术著作信息
    private int user_permission;         //用户权限

    public String getUser_alias() {
        return user_alias;
    }

    public void setUser_alias(String user_alias) {
        this.user_alias = user_alias;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getUser_clipue() {
        return user_clipue;
    }

    public void setUser_clipue(String user_clipue) {
        this.user_clipue = user_clipue;
    }

    public String getUser_jobtitle() {
        return user_jobtitle;
    }

    public void setUser_jobtitle(String user_jobtitle) {
        this.user_jobtitle = user_jobtitle;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_telephone() {
        return user_telephone;
    }

    public void setUser_telephone(String user_telephone) {
        this.user_telephone = user_telephone;
    }

    public String getUser_research_pro_ids() {
        return user_research_pro_ids;
    }

    public void setUser_research_pro_ids(String user_research_pro_ids) {
        this.user_research_pro_ids = user_research_pro_ids;
    }

    public String getUser_research_paper_ids() {
        return user_research_paper_ids;
    }

    public void setUser_research_paper_ids(String user_research_paper_ids) {
        this.user_research_paper_ids = user_research_paper_ids;
    }

    public String getUser_composition_ids() {
        return user_composition_ids;
    }

    public void setUser_composition_ids(String user_composition_ids) {
        this.user_composition_ids = user_composition_ids;
    }

    public int getUser_permission() {
        return user_permission;
    }

    public void setUser_permission(int user_permission) {
        this.user_permission = user_permission;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                ", user_alias='" + user_alias + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_birthday=" + user_birthday +
                ", user_clipue='" + user_clipue + '\'' +
                ", user_jobtitle='" + user_jobtitle + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_telephone='" + user_telephone + '\'' +
                ", user_research_pro_ids='" + user_research_pro_ids + '\'' +
                ", user_research_paper_ids='" + user_research_paper_ids + '\'' +
                ", user_composition_ids='" + user_composition_ids + '\'' +
                ", user_permission=" + user_permission +
                '}';
    }
}
