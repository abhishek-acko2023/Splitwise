package com.project.splitwise.service;

import com.project.splitwise.model.Balance;
import com.project.splitwise.model.User;
import com.project.splitwise.responseModel.SettleBalance;
import com.project.splitwise.responseModel.UserBalanceLog;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalanceService {
    public static BalanceDao balancedao ;
    public static UserDao userDao ;
    @Autowired
    public BalanceService(BalanceDao balancedao , UserDao userDao) {
        BalanceService.balancedao = balancedao ;
        BalanceService.userDao = userDao ;
    }


    public void createBalance(Balance balance){
//        System.out.println(balance.getDonorId() + " " + balance.getReceiverId());
        balancedao.save(balance);
    }
    public void updateBalance(Integer donor_id,Integer receiver_id , double amountToPay){
      List<Balance> updateBalances = balancedao.findAllById(Collections.singletonList(donor_id));
      for(Balance x:updateBalances){
          if(x.getReceiverId().equals(receiver_id)) {
              x.setBalance(x.getBalance() + amountToPay);
              balancedao.save(x);
          }
      }
    }

    public List<Balance> getBalance(){
        List<Balance> allBalances = new ArrayList<>();
        for(Balance balance : balancedao.findAll()){
            if(!balance.getDonorId().equals(balance.getReceiverId())){
                allBalances.add(balance);
            }
        }
        return allBalances;
    }

    public double balanceWithUser(Integer donorId , Integer receiverId){
        double netAmount = 0 ;
        for(Balance balance : balancedao.findAll()){
            if(balance.getDonorId().equals(donorId) && balance.getReceiverId().equals(receiverId))
                netAmount += balance.getBalance();
            if(balance.getDonorId().equals(receiverId) && balance.getReceiverId().equals(donorId))
                netAmount -= balance.getBalance();
        }
        return netAmount ;
    }

    public List<UserBalanceLog> getUserBalanceLog(Integer userId){
        List<UserBalanceLog> userBalanceLogs = new ArrayList<>() ;
        for(User user : userDao.findAll()){
            if(!user.getUserId().equals(userId)){
                userBalanceLogs.add(new UserBalanceLog(user,balanceWithUser(userId,user.getUserId())));
            }
        }
        return userBalanceLogs ;
    }

    public void settleBalance(SettleBalance settleBalance){
        for(Balance x: balancedao.findAllById(Collections.singletonList(settleBalance.getUser1()))){
            if(x.getReceiverId().equals(settleBalance.getUser2())){
                x.setBalance(x.getBalance()-settleBalance.getAmount());
                balancedao.save(x);
                return;
            }
        }
    }

    public List<Balance> getUserGroupBalance(Integer groupId, Integer userId) {
        List <Balance> userGroupBalance = new ArrayList<>();
        for(Balance balance : balancedao.findAll()){
            if(balance.getGroupId().equals(groupId) && balance.getDonorId().equals(userId) && !balance.getDonorId().equals(balance.getReceiverId())){
                userGroupBalance.add(balance);
            }
        }
        return userGroupBalance;
    }
}
