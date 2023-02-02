package io.wcq.concurrent.lock;

/**
 * @author: Chaoqun Wu
 * @description 用不同的synchronized锁实现互斥锁
 * @date: 2023/2/2 11:03
 */
public class AlipayAccount {
    // 余额锁
    private final Object balanceLock = new Object();
    // 密码锁
    private final Object passwordLock = new Object();
    // 账户余额
    private Integer balance;
    // 密码
    private String password;
    // 支付方法
    public void pay(Integer money) {
        synchronized (balanceLock) {
            if (this.balance > money) {
                this.balance -= money;
            }
        }
    }
    // 查看账户中的余额
    public Integer getBalance(){
        synchronized (balanceLock) {
            return this.balance;
        }
    }
    // 修改密码
    public void updatePassword(String newPassword) {
        synchronized (passwordLock) {
            this.password = newPassword;
        }
    }
    // 查看密码
    public String getPassword(){
        synchronized (passwordLock) {
            return this.password;
        }
    }
}
