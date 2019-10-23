package ivan.vatlin.services;

import ivan.vatlin.dto.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountFilesService {
    private static final String accountFolderPath = "money_transfer/src/main/resources/accounts/";
    private static final String prefix = "Id";

    public void writeAccountToFile(Account account) throws IOException {
        File fileFolder = new File(accountFolderPath);
        fileFolder.mkdir();
        String fileName = accountFolderPath + prefix + account.getId();
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(account);
        }
    }

    public void writeAccountsToFiles(List<Account> accounts) throws IOException {
        for (Account account : accounts) {
            writeAccountToFile(account);
        }
    }

    public List<Account> readAccountsFromFiles() throws IOException, ClassNotFoundException {
        File fileFolder = new File(accountFolderPath);
        List<Account> accounts = new ArrayList<>();

        File[] files = fileFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try (FileInputStream fileInputStream = new FileInputStream(file);
                         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                        Account account = (Account) objectInputStream.readObject();
                        accounts.add(account);
                    }
                }
            }
        }
        return accounts;
    }
}
