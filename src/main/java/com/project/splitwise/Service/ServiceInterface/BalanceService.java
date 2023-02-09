package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dto.Request.SettleBalance;
import com.project.splitwise.Dto.Response.Balance.BalanceDTO;
import com.project.splitwise.Dto.Response.Balance.SettledBalanceDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BalanceService {
    List<BalanceDTO> getBalance();
    Object balanceWithUser(Integer payerId, Integer receiverId);
    List<BalanceDTO> getUserBalanceLog(Integer userId);
    Object settleUserBalance(SettleBalance settleBalance);
}
