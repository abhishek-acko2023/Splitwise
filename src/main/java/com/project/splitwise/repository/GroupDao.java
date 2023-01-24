package com.project.splitwise.repository;

import com.project.splitwise.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface GroupDao extends JpaRepository<Groups,Integer> {
}
