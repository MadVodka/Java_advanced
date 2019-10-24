package ivan.vatlin.generators;

import ivan.vatlin.dto.Account;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountsCreator {
    private int quantity;
    private Random random = new Random();
    private int maxBalance;

    public AccountsCreator(int quantity, int maxBalance) {
        if (quantity < 0 || maxBalance < 0) {
            throw new IllegalArgumentException("Значения количества аккаунтов и максимального баланса аккаунта" +
                    " должны быть положительными");
        }
        this.maxBalance = maxBalance;
        this.quantity = quantity;
    }

    public List<Account> create() {
        return IntStream.range(0, quantity)
                .mapToObj(id -> new Account(id, random.nextInt(maxBalance)))
                .collect(Collectors.toList());
    }
}
