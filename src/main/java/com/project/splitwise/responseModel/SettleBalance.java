package com.project.splitwise.responseModel;

public class SettleBalance {
    private Integer user1;
    private Integer user2 ;
    private double amount ;

    private Integer groupId ;

    public SettleBalance(Integer user1, Integer user2, double amount , Integer groupId) {
        this.user1 = user1;
        this.user2 = user2;
        this.amount = amount;
        this.groupId = groupId ;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUser1() {
        return user1;
    }

    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    public Integer getUser2() {
        return user2;
    }

    public void setUser2(Integer user2) {
        this.user2 = user2;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
