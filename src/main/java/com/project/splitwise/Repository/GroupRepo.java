package com.project.splitwise.Repository;

import com.project.splitwise.Dao.GroupDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GroupRepo extends JpaRepository<GroupDao,Integer> {
    Optional<GroupDao> findByGroupId(Integer groupId);
}
