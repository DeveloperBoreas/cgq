package com.example.boreas.repositorys;

import com.example.boreas.model.Clipue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClipueRepository extends JpaRepository<Clipue,Integer> {

//    @Query(name = "findClipueById", value = "select * from Clipue when id = ?1", nativeQuery = true)
//    Clipue findById(Integer integer);

    @Override
    List<Clipue> findAllById(Iterable<Integer> iterable);
}
