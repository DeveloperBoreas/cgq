package com.example.boreas.repositorys;

import com.example.boreas.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    List<UserInfo> findAll();

    @Query(name = "findByNameAndPsd", value = "select * from user_info where user_name = ?1 and user_password=?2", nativeQuery = true)
    UserInfo findByUser_nameAndUser_password(String name, String psd);

}
