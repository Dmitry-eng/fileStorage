package com.example.demo.repository;

import com.example.demo.model.FileGroup;
import com.example.demo.model.Files;
import com.example.demo.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
    List<FileGroup> findAllByFileAndGroup(Files files, Group group);

    @Transactional
    void deleteByFileAndGroup(Files files, Group group);
}
