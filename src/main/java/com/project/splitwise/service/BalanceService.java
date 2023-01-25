package com.project.splitwise.service;

import com.project.splitwise.dto.BalanceDTO;
import com.project.splitwise.dto.BalancelogDTO;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.User;
import com.project.splitwise.responseModel.Response;
import com.project.splitwise.responseModel.SettleBalance;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


    public BalanceDTO convertEntityToDto(Balance balance){
        BalanceDTO balanceDTO = new BalanceDTO() ;
        String donorName ="" ;
        String receiverName = "" ;
        for(User user: userDao.findAll()){
            if(user.getUserId().equals(balance.getDonorId()))donorName=user.getUserName();
            if(user.getUserId().equals(balance.getReceiverId()))receiverName=user.getUserName();
        }
        balanceDTO.setDonorName(donorName);
        balanceDTO.setReceiverName(receiverName);
        balanceDTO.setAmount(balance.getBalance());
        balanceDTO.setGroupName(balance.getGroupName());
        return balanceDTO ;
    }

    public List<BalanceDTO> getBalance(){
        return balancedao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()) ;
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

    public List<BalancelogDTO> getUserBalanceLog(Integer userId){
        List<BalancelogDTO> userBalanceLogs = new ArrayList<>() ;
        for(User user : userDao.findAll()){
            if(!user.getUserId().equals(userId)){
                userBalanceLogs.add(new BalancelogDTO(user.getUserName(),balanceWithUser(userId,user.getUserId())));
            }
        }
        return userBalanceLogs ;
    }

    public Response settleBalance(SettleBalance settleBalance){
        for(Balance balance : balancedao.findAll()){
            if(balance.getReceiverId().equals(settleBalance.getUser2())&&
                    balance.getDonorId().equals(settleBalance.getUser1())&&
                    balance.getGroupId().equals(settleBalance.getGroupId())){
                balance.setBalance(balance.getBalance()-settleBalance.getAmount());
                balancedao.save(balance);
            }
        }
        return new Response("Balance Updated!!" , 200) ;
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
