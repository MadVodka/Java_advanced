package ivan.vatlin.dto;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private long id;
    private long balance;
    private Lock lock = new ReentrantLock();

    public Account(long id, long balance) {
        if (id < 0) {
            throw new IllegalArgumentException("Значение id клинета не может быть мешь нуля");
        }
        this.id = id;
        if (balance < 0) {
            throw new IllegalArgumentException("Сумма не может быть меньше нуля");
        }
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void lockObject() {
        lock.lock();
    }

    public void unlockObject() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", sum=" + balance +
                '}'+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
