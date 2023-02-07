package com.project.splitwise.dao;

import com.project.splitwise.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GroupDao extends JpaRepository<Groups,Integer> {
}
