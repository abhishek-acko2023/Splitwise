package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dto.Request.SettleBalance;
import com.project.splitwise.Dto.Response.Balance.BalanceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BalanceService {
    Optional<Object> getBalance();
    Object balanceWithUser(Integer payerId, Integer receiverId);
    List<BalanceDTO> getUserBalanceLog(Integer userId);
    Object settleUserBalance(SettleBalance settleBalance);
}
