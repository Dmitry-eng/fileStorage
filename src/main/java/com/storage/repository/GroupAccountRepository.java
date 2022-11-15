package com.storage.repository;

import com.storage.model.Account;
import com.storage.model.Group;
import com.storage.model.GroupAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Long> {

    int countByGroupAndAccount(Group group, Account account);

    GroupAccount findByGroupAndAccount(Group group, Account account);

    @Transactional
    void deleteByGroupAndAccount(Group group, Account account);
}
