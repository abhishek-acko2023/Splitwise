package com.project.splitwise.interfaces;

import com.project.splitwise.model.Expense;

public interface SplitService {
    public enum Split{
        EQUAL,CUSTOM,PERCENTAGE ;
    }

    void splitExpense(Expense expense) ;
}
