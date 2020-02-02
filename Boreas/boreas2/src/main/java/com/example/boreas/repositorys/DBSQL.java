package com.example.boreas.repositorys;

public interface DBSQL {
    public static String QUERY_BY_NAME_PSD = "select * from UserInfo where user_name = ?1 and user_password=?2";
}
