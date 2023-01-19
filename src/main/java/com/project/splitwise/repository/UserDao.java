package com.project.splitwise.repository;

import com.project.splitwise.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
public interface UserDao extends JpaRepository<User,Integer> {
}
