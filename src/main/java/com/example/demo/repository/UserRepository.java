package com.example.demo.repository;


import com.example.demo.model.Group;
import com.example.demo.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @NotNull List<User> findAll();

    List<User> findAllByEmailContains(String email);

    List<User> findAllByLoginContains(String login);

    User findByLogin(String login);

    @Query("SELECT gu.user FROM GroupUser gu WHERE gu.group=:group")
    List<User> pFindByGroup(@Param(value = "group") Group group);


}
