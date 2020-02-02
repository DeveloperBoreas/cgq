package com.example.boreas.model;


import javax.persistence.*;

/**
 * 系别
 */
@Entity
@Table(name = "clipue")
public class Clipue {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String clipue_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClipue_name() {
        return clipue_name;
    }

    public Clipue setClipue_name(String clipue_name) {
        this.clipue_name = clipue_name;
        return this;
    }


}
