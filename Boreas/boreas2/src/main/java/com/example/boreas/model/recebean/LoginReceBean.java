package com.example.boreas.model.recebean;


import java.util.ArrayList;

public class LoginReceBean {
    private Integer user_id; //用户 id
    private String name;
    private String psd;
    private String user_alias;
    private String user_sex;                //性别
    private String user_birthday;           //生日
    private ArrayList<Clipue> user_clipues;
    private String user_jobtitle;           //职称
    private String user_address;            //地址
    private String user_telephone;          //手机号码
    private ArrayList<ResearchPro> researchPros;
    private ArrayList<ResearchPaper> researchPapers;
    private ArrayList<Composition> compositions;
    private int user_permission;         //用户权限

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_alias() {
        return user_alias;
    }

    public void setUser_alias(String user_alias) {
        this.user_alias = user_alias;
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

    public ArrayList<Clipue> getUser_clipues() {
        return user_clipues;
    }

    public void setUser_clipues(ArrayList<Clipue> user_clipues) {
        this.user_clipues = user_clipues;
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

    public ArrayList<ResearchPro> getResearchPros() {
        return researchPros;
    }

    public void setResearchPros(ArrayList<ResearchPro> researchPros) {
        this.researchPros = researchPros;
    }

    public ArrayList<ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    public void setResearchPapers(ArrayList<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }

    public ArrayList<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(ArrayList<Composition> compositions) {
        this.compositions = compositions;
    }

    public int getUser_permission() {
        return user_permission;
    }

    public void setUser_permission(int user_permission) {
        this.user_permission = user_permission;
    }

    //所属系
    public static class Clipue {
        private int id;
        private String clipue_name;

        public Clipue(int id, String clipue_name) {
            this.id = id;
            this.clipue_name = clipue_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getClipue_name() {
            return clipue_name;
        }

        public void setClipue_name(String clipue_name) {
            this.clipue_name = clipue_name;
        }
    }


    //科研项目信息
    public static class ResearchPro {
        private int id;
        private String pro_name;               //项目名称
        private int pro_level;              //项目级别
        private String pro_money;              //项目经费
        private String pro_finish_date;        //完成日期
        private String pro_current_status;     //当前状态
        private String pro_bear_palm;          //获奖情况

        public ResearchPro(int id, String pro_name, int pro_level, String pro_money, String pro_finish_date, String pro_current_status, String pro_bear_palm) {
            this.id = id;
            this.pro_name = pro_name;
            this.pro_level = pro_level;
            this.pro_money = pro_money;
            this.pro_finish_date = pro_finish_date;
            this.pro_current_status = pro_current_status;
            this.pro_bear_palm = pro_bear_palm;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public int getPro_level() {
            return pro_level;
        }

        public void setPro_level(int pro_level) {
            this.pro_level = pro_level;
        }

        public String getPro_money() {
            return pro_money;
        }

        public void setPro_money(String pro_money) {
            this.pro_money = pro_money;
        }

        public String getPro_finish_date() {
            return pro_finish_date;
        }

        public void setPro_finish_date(String pro_finish_date) {
            this.pro_finish_date = pro_finish_date;
        }

        public String getPro_current_status() {
            return pro_current_status;
        }

        public void setPro_current_status(String pro_current_status) {
            this.pro_current_status = pro_current_status;
        }

        public String getPro_bear_palm() {
            return pro_bear_palm;
        }

        public void setPro_bear_palm(String pro_bear_palm) {
            this.pro_bear_palm = pro_bear_palm;
        }
    }

    //科研论文信息
    public static class ResearchPaper {
        private int id;
        private String paper_name;             //论文题目
        private String paper_author;           //第一作者
        private String paper_otherauthor;      //其他作者
        private String paper_periodical_code;  //刊物编码
        private String paper_category;         //类别
        private String paper_publish_date;     //发表日期
        private String paper_bear_palm;        //获奖情况

        public ResearchPaper(int id, String paper_name, String paper_author, String paper_otherauthor, String paper_periodical_code, String paper_category, String paper_publish_date, String paper_bear_palm) {
            this.id = id;
            this.paper_name = paper_name;
            this.paper_author = paper_author;
            this.paper_otherauthor = paper_otherauthor;
            this.paper_periodical_code = paper_periodical_code;
            this.paper_category = paper_category;
            this.paper_publish_date = paper_publish_date;
            this.paper_bear_palm = paper_bear_palm;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPaper_name() {
            return paper_name;
        }

        public void setPaper_name(String paper_name) {
            this.paper_name = paper_name;
        }

        public String getPaper_author() {
            return paper_author;
        }

        public void setPaper_author(String paper_author) {
            this.paper_author = paper_author;
        }

        public String getPaper_otherauthor() {
            return paper_otherauthor;
        }

        public void setPaper_otherauthor(String paper_otherauthor) {
            this.paper_otherauthor = paper_otherauthor;
        }

        public String getPaper_periodical_code() {
            return paper_periodical_code;
        }

        public void setPaper_periodical_code(String paper_periodical_code) {
            this.paper_periodical_code = paper_periodical_code;
        }

        public String getPaper_category() {
            return paper_category;
        }

        public void setPaper_category(String paper_category) {
            this.paper_category = paper_category;
        }

        public String getPaper_publish_date() {
            return paper_publish_date;
        }

        public void setPaper_publish_date(String paper_publish_date) {
            this.paper_publish_date = paper_publish_date;
        }

        public String getPaper_bear_palm() {
            return paper_bear_palm;
        }

        public void setPaper_bear_palm(String paper_bear_palm) {
            this.paper_bear_palm = paper_bear_palm;
        }
    }

    //学术著作信息
    public static class Composition {
        private int id;
        private String book_name;               //书籍名称
        private String book_auther;             //第一作者
        private String book_other_auther;       //其他作者
        private String book_periodical_code;    //刊物编码
        private String book_press;              //出版社
        private String book_press_date;         //出版日期
        private String book_char_num;           //书籍字数
        private String book_money;              //单价
        private String book_bear_palm;          //获奖情况

        public Composition(int id, String book_name, String book_auther, String book_other_auther, String book_periodical_code, String book_press, String book_press_date, String book_char_num, String book_money, String book_bear_palm) {
            this.id = id;
            this.book_name = book_name;
            this.book_auther = book_auther;
            this.book_other_auther = book_other_auther;
            this.book_periodical_code = book_periodical_code;
            this.book_press = book_press;
            this.book_press_date = book_press_date;
            this.book_char_num = book_char_num;
            this.book_money = book_money;
            this.book_bear_palm = book_bear_palm;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_auther() {
            return book_auther;
        }

        public void setBook_auther(String book_auther) {
            this.book_auther = book_auther;
        }

        public String getBook_other_auther() {
            return book_other_auther;
        }

        public void setBook_other_auther(String book_other_auther) {
            this.book_other_auther = book_other_auther;
        }

        public String getBook_periodical_code() {
            return book_periodical_code;
        }

        public void setBook_periodical_code(String book_periodical_code) {
            this.book_periodical_code = book_periodical_code;
        }

        public String getBook_press() {
            return book_press;
        }

        public void setBook_press(String book_press) {
            this.book_press = book_press;
        }

        public String getBook_press_date() {
            return book_press_date;
        }

        public void setBook_press_date(String book_press_date) {
            this.book_press_date = book_press_date;
        }

        public String getBook_char_num() {
            return book_char_num;
        }

        public void setBook_char_num(String book_char_num) {
            this.book_char_num = book_char_num;
        }

        public String getBook_money() {
            return book_money;
        }

        public void setBook_money(String book_money) {
            this.book_money = book_money;
        }

        public String getBook_bear_palm() {
            return book_bear_palm;
        }

        public void setBook_bear_palm(String book_bear_palm) {
            this.book_bear_palm = book_bear_palm;
        }
    }
}
