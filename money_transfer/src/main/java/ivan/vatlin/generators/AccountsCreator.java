package ivan.vatlin.generators;

import ivan.vatlin.dto.Account;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountsCreator {
    private int quantity;
    private Random random = new Random();
    private int maxAccountSum;

    public AccountsCreator(int quantity, int maxAccountSum) {
        if (quantity < 0 || maxAccountSum < 0) {
            throw new IllegalArgumentException("Значения должны быть положительными");
        }
        this.maxAccountSum = maxAccountSum;
        this.quantity = quantity;
    }

    public List<Account> create() {
        return IntStream.range(0, quantity)
                .mapToObj(id -> new Account(id, random.nextInt(maxAccountSum)))
                .collect(Collectors.toList());
    }
}
