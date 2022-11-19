package com.storage.repository;

import com.storage.model.Account;
import com.storage.model.File;
import com.storage.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long>, JpaSpecificationExecutor<Account> {
    @Query("SELECT fg.file FROM FileGroup fg " +
            "WHERE fg.group=:group")
    List<File> findAllByGroup(@Param(value = "group") Group group);

    List<File> findAllByAccount(Account account);

    @Query("SELECT file FROM File file WHERE file.account=:account and (file.name like %:value% or" +
            " file.info like %:value% or CAST(file.id as text) = :value)")
    List<File> findAllByAccount(@Param(value = "account") Account account, @Param(value = "value") String value);

    List<File> findAllByOpenTrueOrAccount(Account account);

    @Query("SELECT file FROM File file " +
            "left join FileGroup fg on fg.file=file " +
            "left join GroupAccount ga on ga.group=fg.group " +
            "WHERE file.open=true or file.account=:account or ga.account=:account")
    List<File> findAll(@Param(value = "account") Account account);

    @Query("SELECT file FROM File file WHERE file.name like %:value% or file.info like %:value% or CAST(file.id as text) like %:value%")
    List<File> findAll(@Param(value = "value") String value);

    @Query("SELECT file FROM File file " +
            "inner join FileGroup fg on fg.file=file " +
            "inner join GroupAccount ga on ga.group=fg.group  " +
            "WHERE (file.open=true or file.account=:account or ga.account=:account) and (file.name like %:value% or " +
            "file.info like %:value%  or   CAST(file.id as text) = :value)")
    List<File> findAll(@Param(value = "account") Account account, @Param(value = "value") String value);

    void deleteByAccount(Account account);
}
