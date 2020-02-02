package com.example.boreas.repositorys;


import com.example.boreas.model.ResearchPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Integer> {

}
