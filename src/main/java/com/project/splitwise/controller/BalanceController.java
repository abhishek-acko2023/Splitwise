package com.project.splitwise.controller;

import com.project.splitwise.dto.BalanceDTO;
import com.project.splitwise.dto.BalancelogDTO;
import com.project.splitwise.model.Balance;
import com.project.splitwise.responseModel.Response;
import com.project.splitwise.responseModel.SettleBalance;
import com.project.splitwise.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/balance")
public class BalanceController {
 private static BalanceService balanceService ;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        BalanceController.balanceService = balanceService ;
    }

    public static void createBalance(Balance balance){
     balanceService.createBalance(balance);
    }

    public static void updateBalance(Integer donor_id , Integer receiver_id , double amountToPay){
        balanceService.updateBalance(donor_id,receiver_id,amountToPay);
    }

    @GetMapping("/") public List<BalanceDTO> getBalance(){
        return balanceService.getBalance() ;
    }

    @GetMapping("/{donorId}/{receiverId}")
    public double balanceWithUser(@PathVariable("donorId") Integer donorId , @PathVariable("receiverId") Integer receiverId){
        return balanceService.balanceWithUser(donorId,receiverId) ;}

    @GetMapping("/{userId}")
    public List<BalancelogDTO> getUserBalanceLog(@PathVariable("userId") Integer userId){
        return balanceService.getUserBalanceLog(userId);
    }

    @PutMapping("/settle")
    public Response settleBalance(@RequestBody SettleBalance settleBalance){
        return balanceService.settleBalance(settleBalance);
    }

}
