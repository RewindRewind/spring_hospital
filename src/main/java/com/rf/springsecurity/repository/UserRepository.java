package com.rf.springsecurity.repository;
import com.rf.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

//    @Query(value="SELECT u FROM User u LEFT JOIN FETCH u.userInfo user")
//    List<User> findAllUsers();
//
//    @Modifying
//    @Query("update User u set u.userInfo = ?1 where u.id = ?2")
//    void updateUserWithUserInfo(UserInfo userInfo, Long userId);
}