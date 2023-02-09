package com.project.splitwise.Service.ServiceImpls;

import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.SettleBalance;
import com.project.splitwise.Dto.Response.Balance.SettledBalanceDTO;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Service.ServiceInterface.BalanceService;
import com.project.splitwise.Dto.Response.Balance.BalanceDTO;
import com.project.splitwise.Dao.BalanceDao;
import com.project.splitwise.Repository.BalanceRepo;
import com.project.splitwise.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalanceServiceImpls implements BalanceService {
    public static BalanceRepo balanceRepo;
    public static UserRepo userRepo;
    @Autowired
    BalanceServiceImpls(BalanceRepo balanceRepo, UserRepo userRepo) {
        BalanceServiceImpls.balanceRepo = balanceRepo;
        BalanceServiceImpls.userRepo = userRepo;
    }

    public BalanceDTO generateBalanceDTO(BalanceDao balanceDao){
        BalanceDTO balanceDTO = new BalanceDTO();
        UserDTO payer = new UserDTO();
        UserDTO receiver = new UserDTO();
        for(UserDao userDao : userRepo.findAll()){
            if(userDao.getUserId().equals(balanceDao.getPayerId())){
                payer.setUserName(userDao.getUserName());
                payer.setUserEmail(userDao.getUserEmail());
            }
            if(userDao.getUserId().equals(balanceDao.getReceiverId())) {
                receiver.setUserName(userDao.getUserName());
                receiver.setUserEmail(userDao.getUserEmail());
            }
        }
        balanceDTO.setPayer(payer);
        balanceDTO.setReceiver(receiver);
        balanceDTO.setAmount(balanceDao.getBalance());
        balanceDTO.setMessage(payer.getUserName() + " will pay amount : ₹" + balanceDao.getBalance()+" to " + receiver.getUserName());
        return balanceDTO ;
    }

    public Optional<Object> getBalance(){
        List<BalanceDao> allBalances = balanceRepo.findAll();
        List<BalanceDTO> balances = new ArrayList<>();
        for(BalanceDao balanceDao : allBalances){
            if(balanceDao.getPayerId().equals(balanceDao.getReceiverId())) continue;
            balances.add(generateBalanceDTO(balanceDao));
        }
        if(balances.size()==0){
            return Optional.of("No balances present") ;
        }
        return Optional.of(balances);
    }

    public Object balanceWithUser(Integer payerId , Integer receiverId){
        double netAmount = 0;
        for(BalanceDao balanceDao: balanceRepo.findAll()){
            if(balanceDao.getPayerId().equals(payerId) && balanceDao.getReceiverId().equals(receiverId)) {
                netAmount += balanceDao.getBalance();
            }
            if(balanceDao.getPayerId().equals(receiverId) && balanceDao.getReceiverId().equals(payerId)) {
                netAmount -= balanceDao.getBalance();
            }
        }
        BalanceDTO balanceDTO = new BalanceDTO();
        UserDTO payer = new UserDTO();
        UserDTO receiver = new UserDTO();

        if(netAmount == 0){
            return new SettledBalanceDTO(0d,"Cheers you are settled up!");
        }

        for(UserDao userDao : userRepo.findAll()){
            if(userDao.getUserId().equals(payerId)){
                payer.setUserName(userDao.getUserName());
                payer.setUserEmail(userDao.getUserEmail());
            }
            if(userDao.getUserId().equals(receiverId)) {
                receiver.setUserName(userDao.getUserName());
                receiver.setUserEmail(userDao.getUserEmail());
            }
        }

        if(netAmount > 0){
            balanceDTO.setPayer(payer);
            balanceDTO.setReceiver(receiver);
            balanceDTO.setAmount(netAmount);
            balanceDTO.setMessage(payer.getUserName() + " will pay amount : ₹ " + netAmount +" to " + receiver.getUserName());
            return balanceDTO ;
        }

        if(netAmount < 0){
            balanceDTO.setPayer(receiver);
            balanceDTO.setReceiver(payer);
            balanceDTO.setAmount(Math.abs(netAmount));
            balanceDTO.setMessage(payer.getUserName() + " will receive amount : ₹ " + Math.abs(netAmount) +" from " + receiver.getUserName());
            return balanceDTO ;
        }
        return null;
    }
    public List<BalanceDTO> getUserBalanceLog(Integer userId){
        List<BalanceDao> allBalances = balanceRepo.findAll();
        List<BalanceDTO> userBalanceLogs = new ArrayList<>() ;
        for(BalanceDao balanceDao : allBalances){
            if(balanceDao.getPayerId().equals(balanceDao.getReceiverId()) || balanceDao.getBalance() == 0) continue;
            if(balanceDao.getPayerId().equals(userId) || balanceDao.getReceiverId().equals(userId)) {
                userBalanceLogs.add(generateBalanceDTO(balanceDao));
            }
        }
        return userBalanceLogs;
    }
    public Object settleUserBalance(SettleBalance settleBalance){
        Optional<UserDao> payer = userRepo.findByUserEmail(settleBalance.getPayerEmail());
        Optional<UserDao> receiver = userRepo.findByUserEmail(settleBalance.getReceiverEmail());

        if(payer.isEmpty() || receiver.isEmpty()){
            return "invalid settle balance query!";
        }

        if(settleBalance.getGroupId() == null){
            for(BalanceDao balanceDao: balanceRepo.findAll()){
                if(balanceDao.getPayerId().equals(payer.get().getUserId()) &&
                    balanceDao.getReceiverId().equals(receiver.get().getUserId())){
                    if(settleBalance.getSettleAmount() > balanceDao.getBalance()){
                        return "Invalid settleAmount. Cannot be greater than due amount!";
                    }
                    balanceDao.setBalance(balanceDao.getBalance() - settleBalance.getSettleAmount());
                    SettledBalanceDTO res = new SettledBalanceDTO(balanceDao.getBalance(),"Amount settled.");
                    balanceRepo.save(balanceDao);
                    return res;
                }
            }
        }

        for(BalanceDao balanceDao: balanceRepo.findAll()){
            if(balanceDao.getPayerId().equals(payer.get().getUserId()) &&
                    balanceDao.getReceiverId().equals(receiver.get().getUserId()) &&
                    balanceDao.getGroupId().equals(settleBalance.getGroupId())){
                if(settleBalance.getSettleAmount() > balanceDao.getBalance()){
                    return "Invalid settleAmount. Cannot be greater than due amount!";
                }
                balanceDao.setBalance(balanceDao.getBalance() - settleBalance.getSettleAmount());
                SettledBalanceDTO res = new SettledBalanceDTO(balanceDao.getBalance(),"Amount settled.");
                balanceRepo.save(balanceDao);
                return res;
            }
        }

        return "invalid settle balance query!";
    }

}
