package com.storage.repository;

import com.storage.model.File;
import com.storage.model.FileGroup;
import com.storage.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FileGroupRepository extends JpaRepository<FileGroup, Long> {
    List<FileGroup> findAllByFileAndGroup(File file, Group group);

    @Transactional
    void deleteByFileAndGroup(File file, Group group);
}
