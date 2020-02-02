package com.example.boreas.repositorys;


import com.example.boreas.model.ComPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComPositionRepository extends JpaRepository<ComPosition,Integer> {
}
