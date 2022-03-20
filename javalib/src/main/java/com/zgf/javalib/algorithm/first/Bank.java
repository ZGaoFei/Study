package com.zgf.javalib.algorithm.first;

// 2043：简单交易系统
class Bank {
    private long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        long money1 = balance[account1];
        if (account1 > balance.length || account2 > balance.length || money1 < money) {
            return false;
        }
        balance[account1 - 1] = money1 - money;
        balance[account2 - 1] = balance[account2 - 1] + money;

        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > balance.length) {
            return false;
        }
        balance[account - 1] = balance[account - 1] + money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        long currentMoney = balance[account - 1];
        if (account > balance.length || currentMoney < money) {
            return false;
        }
        balance[account] = currentMoney - money;
        return true;
    }
}
