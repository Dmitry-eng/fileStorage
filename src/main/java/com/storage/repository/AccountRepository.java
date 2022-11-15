package com.storage.repository;

import com.storage.model.Account;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @NotNull List<Account> findAll();

    List<Account> findAllByEmailContains(String email);

    List<Account> findAllByLoginContains(String login);

    Account findByLogin(String login);

    @Query("SELECT ga.account FROM GroupAccount ga WHERE ga.group.id=:id")
    List<Account> pFindByGroupId(@Param(value = "id") Long id);
}
