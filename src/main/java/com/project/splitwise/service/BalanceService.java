package com.project.splitwise.service;

import com.project.splitwise.local.BalanceList;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.User;
//import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.responseModel.SettleBalance;
import com.project.splitwise.responseModel.UserBalanceLog;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//import static jdk.internal.vm.vector.VectorSupport.convert;

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
        return balancedao.findAll() ;
    }

        public double balanceWithUser(Integer donorId , Integer receiverId){
        double netAmount = 0 ;
        for(Balance x: balancedao.findAll()){
            if(x.getDonorId().equals(donorId)&&x.getReceiverId().equals(receiverId)) netAmount += x.getBalance() ;
            if(x.getDonorId().equals(receiverId)&&x.getReceiverId().equals(donorId)) netAmount -= x.getBalance() ;
        }
        return netAmount ;
//        if(netAmount<0)return donorUser.getUserName() +" " + "owes" + " " + netAmount + " " + "to " + receiverUser.getUserName() ;
//        else return receiverUser.getUserName() +" " + "owes" + " " + netAmount + " " + "to " + donorUser.getUserName();
    }
    public List<UserBalanceLog> getUserBalanceLog(Integer userId){
        List<UserBalanceLog> userBalanceLogs = new ArrayList<>() ;
        for(User x:userDao.findAll()){
            if(!x.getUserId().equals(userId)){
                userBalanceLogs.add(new UserBalanceLog(x,balanceWithUser(userId,x.getUserId())));
            }
        }
        return userBalanceLogs ;
    }

    public void settleBalance(SettleBalance settleBalance){
        for(Balance x: balancedao.findAllById(Collections.singletonList(settleBalance.getUser1()))){
            if(x.getReceiverId().equals(settleBalance.getUser2())){
                System.out.println(x.getBalance() + " " + settleBalance.getAmount());
                x.setBalance(x.getBalance()-settleBalance.getAmount());
                balancedao.save(x);
                return;
            }
        }
    }

}
