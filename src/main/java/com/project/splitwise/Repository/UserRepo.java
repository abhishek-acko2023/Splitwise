package com.project.splitwise.Repository;

import com.project.splitwise.Dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDao,Integer> {
    Optional<UserDao> findByUserId(String email);
    Optional<UserDao> findByUserEmail(String email);
}
