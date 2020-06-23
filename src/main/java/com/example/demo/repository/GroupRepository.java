package com.example.demo.repository;

import com.example.demo.model.Group;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM  Group g LEFT join g.groupUserList AS gul WHERE gul.user=:user or g.user=:user ")
    List<Group> findAllMyGroup(@Param(value = "user") User user);

    int countByNameEquals(String name);
}
