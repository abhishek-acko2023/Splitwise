package com.project.splitwise.Controller;

import com.project.splitwise.Dto.Request.SettleBalance;
import com.project.splitwise.Dto.Response.Balance.BalanceDTO;
import com.project.splitwise.Dto.Response.Balance.SettledBalanceDTO;
import com.project.splitwise.Service.ServiceInterface.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/balance")
public class BalanceController {
    public static BalanceService balanceService;

    @Autowired
    BalanceController(BalanceService balanceService) {
        BalanceController.balanceService = balanceService;
    }

    @GetMapping("/all") public List<BalanceDTO> getBalance(){
        return balanceService.getBalance() ;
    }

    @GetMapping("/{payerId}/{receiverId}")
    public Object balanceWithUser(@PathVariable("payerId") Integer payerId , @PathVariable("receiverId") Integer receiverId){
        return balanceService.balanceWithUser(payerId,receiverId) ;}

    @GetMapping("/{userId}")
    public List<BalanceDTO> getUserBalanceLog(@PathVariable("userId") Integer userId){
        return balanceService.getUserBalanceLog(userId);
    }

    @PostMapping("/settle")
    public Object settlUserBalance(@RequestBody SettleBalance settleBalance){
        return balanceService.settleUserBalance(settleBalance);
    }

}
