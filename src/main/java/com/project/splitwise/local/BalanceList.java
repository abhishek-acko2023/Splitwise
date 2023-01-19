package com.project.splitwise.local;

import com.project.splitwise.model.Balance;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.service.BalanceService;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BalanceList {
    public static List<Balance> balanceList = new ArrayList<>();

    private static BalanceDao balanceDao ;
    private static BalanceList instance ;
    @Autowired
    private BalanceList(BalanceDao balanceDao) {
        BalanceList.balanceDao = balanceDao;
        balanceList = balanceDao.findAll();
    }

    public static BalanceList getInstance(){
        if(instance==null){
            instance = new BalanceList(BalanceService.balancedao);
        }
        return instance ;
    }
}
