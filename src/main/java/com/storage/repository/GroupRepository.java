package com.storage.repository;

import com.storage.model.Account;
import com.storage.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByAccountsContaining(Account account);

    @Query("SELECT g FROM  Group g  where g.account = :account or :account MEMBER g.accounts")
    List<Group> findAllGroupAccessForAccount(@Param(value = "account") Account account);

    @Query("SELECT g FROM  Group g  where (g.account = :account or :account MEMBER g.accounts) and " +
            "(g.name like %:value% or g.account.login like  %:value%  or CAST(g.id as text) = :value)")
    List<Group> findAllGroupAccessForAccount(@Param(value = "account") Account account, @Param(value = "value") String value);


    @Query("SElECT g FROM Group g WHERE g.name like %:value% or g.account.login like  %:value%  or CAST(g.id as text) = :value")
    List<Group> findAll(@Param(value = "value") String value);

    int countByNameEquals(String name);
}
