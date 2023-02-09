package com.project.splitwise.Repository;

import com.project.splitwise.Dao.BalanceDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepo extends JpaRepository<BalanceDao,Integer> {
}
