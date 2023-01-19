package com.project.splitwise.responseModel;

import com.project.splitwise.model.User;

public class UserBalanceLog {
    private User user ;
    private double balance ;

    public UserBalanceLog(User user, double balance) {
        this.user = user;
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
