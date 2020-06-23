package com.example.demo.repository;

import com.example.demo.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends JpaRepository<Files, Long> {
    @Query("SELECT fg.file FROM FileGroup fg WHERE fg.group=:group")
    List<Files> findAllByGroup(@Param(value = "group") Group group);

    List<Files> findAllByUser(User user_id);

    List<Files> findAllByOpenTrueOrUser(User user);

    Files findByIdAndUser(Long id, User user);
}
