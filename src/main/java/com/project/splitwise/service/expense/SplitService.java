package com.project.splitwise.service.expense;

import com.project.splitwise.model.Expense;
import org.springframework.stereotype.Component;


public interface SplitService {
    public enum Split{
        EQUAL,CUSTOM,PERCENTAGE ;
    }

    void splitExpense(Expense expense) ;
}
