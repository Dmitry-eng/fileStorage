package com.example.demo.repository;

import com.example.demo.model.Group;
import com.example.demo.model.Request;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r join r.group.user u WHERE u=:user")
    List<Request> findAllByUser(@Param(value = "user") User user);

    List<Request> findByUserAndGroup(User user, Group group);
}
