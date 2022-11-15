package com.storage.repository;

import com.storage.model.Account;
import com.storage.model.Group;
import com.storage.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request r join r.group.account a WHERE a=:account")
    List<Request> findAllByAccount(@Param(value = "account") Account account);

    List<Request> findByAccountAndGroup(Account account, Group group);
}
