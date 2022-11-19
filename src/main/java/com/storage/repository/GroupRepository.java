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
    @Query("SELECT g FROM  Group g LEFT join g.groupAccounts AS gul WHERE gul.account=:account or g.account=:account ")
    List<Group> findAllGroupAccessForAccount(@Param(value = "account") Account account);

    @Query("SELECT g FROM  Group g LEFT join g.groupAccounts AS gul WHERE (gul.account=:account or g.account=:account) and " +
            "(g.account.login like  %:value% or g.name like %:value%  or CAST(g.id as text) = :value )")
    List<Group> findAllGroupAccessForAccount(@Param(value = "account") Account account, @Param(value = "value") String value);

    @Query("SElECT g FROM Group g WHERE g.name like %:value% or g.account.login like  %:value%  or CAST(g.id as text) = :value")
    List<Group> findAll(@Param(value = "value") String value);

    int countByNameEquals(String name);
}
