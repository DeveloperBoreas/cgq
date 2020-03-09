package com.example.boreas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * 学术著作信息
 */
@Entity
@Table(name = "com_position")
public class ComPosition {

    @Id
    @GeneratedValue
    private int id;
    private String book_name;               //书籍名称
    private String book_auther;             //第一作者
    private String book_other_auther;       //其他作者
    private String book_periodical_code;    //刊物编码
    private String book_press;              //出版社
    private Date book_press_date;         //出版日期
    private String book_char_num;           //书籍字数
    private String book_money;              //单价
    private String book_bear_palm;          //获奖情况

    public int getId() {
        return id;
    }

    public ComPosition setId(int id) {
        this.id = id;
        return this;
    }

    public String getBook_name() {
        return book_name;
    }

    public ComPosition setBook_name(String book_name) {
        this.book_name = book_name;return this;
    }

    public String getBook_auther() {
        return book_auther;
    }

    public ComPosition setBook_auther(String book_auther) {
        this.book_auther = book_auther;return this;
    }

    public String getBook_other_auther() {
        return book_other_auther;
    }

    public ComPosition setBook_other_auther(String book_other_auther) {
        this.book_other_auther = book_other_auther;return this;
    }

    public String getBook_periodical_code() {
        return book_periodical_code;
    }

    public ComPosition setBook_periodical_code(String book_periodical_code) {
        this.book_periodical_code = book_periodical_code;
        return this;
    }

    public String getBook_press() {
        return book_press;
    }

    public ComPosition setBook_press(String book_press) {
        this.book_press = book_press;
        return this;
    }

    public Date getBook_press_date() {
        return book_press_date;
    }

    public ComPosition setBook_press_date(Date book_press_date) {
        this.book_press_date = book_press_date;return this;
    }

    public String getBook_char_num() {
        return book_char_num;
    }

    public ComPosition setBook_char_num(String book_char_num) {
        this.book_char_num = book_char_num;return this;
    }

    public String getBook_money() {
        return book_money;
    }

    public ComPosition setBook_money(String book_money) {
        this.book_money = book_money;return this;
    }

    public String getBook_bear_palm() {
        return book_bear_palm;
    }

    public ComPosition setBook_bear_palm(String book_bear_palm) {
        this.book_bear_palm = book_bear_palm;return this;
    }

    @Override
    public String toString() {
        return "ComPosition{" +
                "id=" + id +
                ", book_name='" + book_name + '\'' +
                ", book_auther='" + book_auther + '\'' +
                ", book_other_auther='" + book_other_auther + '\'' +
                ", book_periodical_code='" + book_periodical_code + '\'' +
                ", book_press='" + book_press + '\'' +
                ", book_press_date=" + book_press_date +
                ", book_char_num='" + book_char_num + '\'' +
                ", book_money='" + book_money + '\'' +
                ", book_bear_palm='" + book_bear_palm + '\'' +
                '}';
    }
}
