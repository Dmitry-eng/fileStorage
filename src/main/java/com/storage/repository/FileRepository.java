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
    @Query("SELECT g.files FROM Group g where g=:group")
    List<File> findAllByGroup(@Param(value = "group") Group group);

    List<File> findAllByAccount(Account account);

    @Query("SELECT file FROM File file WHERE file.account=:account and (file.name like :value or file.info like :value)")
    List<File> findAllByAccount(@Param(value = "account") Account account, @Param(value = "value") String value);

    List<File> findAllByOpenTrueOrAccount(Account account);

    //    @Query("SELECT file FROM File file where file.account=:account or file.open=true")
    List<File> findAllByAccountOrOpenTrue(Account account);

    @Query("SELECT file FROM File file where (file.account=:account or file.open=true) and (file.name like :value or file.info like :value or file.account.login like :value)")
    List<File> findAllByAccountAndValueContains(@Param("account") Account account, @Param(value = "value") String value);

    @Query("SELECT file FROM File file WHERE file.name like :value or file.info like :value or file.account.login like :value")
    List<File> findAll(@Param(value = "value") String value);


    @Query("SELECT file FROM File file ")
    List<File> findAllByAccountOrOpenTrue(@Param(value = "account") Account account, @Param(value = "value") String value);

    void deleteByAccount(Account account);
}
