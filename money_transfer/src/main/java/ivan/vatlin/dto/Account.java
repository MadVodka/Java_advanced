package ivan.vatlin.dto;

import java.io.Serializable;

public class Account implements Serializable {
    private long id;
    private long sum;

    public Account(long id, long sum) {
        if (id < 0) {
            throw new IllegalArgumentException("Значение id клинета не может быть мешь нуля");
        }
        this.id = id;
        if (sum < 0) {
            throw new IllegalArgumentException("Сумма не может быть меньше нуля");
        }
        this.sum = sum;
    }

    public long getId() {
        return id;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", sum=" + sum +
                '}';
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
