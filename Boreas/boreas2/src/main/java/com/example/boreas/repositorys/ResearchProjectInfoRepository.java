package com.example.boreas.repositorys;


import com.example.boreas.model.ResearchProjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchProjectInfoRepository extends JpaRepository<ResearchProjectInfo, Integer> {
}
