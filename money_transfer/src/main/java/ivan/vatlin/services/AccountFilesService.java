package ivan.vatlin.services;

import ivan.vatlin.dto.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountFilesService {
    private final String accountFolderPath = "money_transfer/src/main/resources/accounts/initial/";
    private final String prefix = "Id";

    public void writeAccountToFile(Account account) throws IOException {
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

//        accounts.forEach(this::writeAccountToFile);
    }

    public List<Account> readAccountsFromFiles() throws IOException, ClassNotFoundException {
        File fileFolder = new File(accountFolderPath);
        List<Account> accounts = new ArrayList<>();

//        try (Stream<Path> walk = Files.walk(Paths.get(accountFolderPath))) {
//            walk.filter(path -> path.startsWith("Id"))
//                    .map(path -> path.toFile())
//                    .map(file -> {
//                        try {
//                            return readAccountFromFile(file);
//                        } catch (IOException e) {
//                            throw e;
//                        } catch (ClassNotFoundException e) {
//                            throw e;
//                        }
//                    })
//                    .collect(Collectors.toList());
//        }

        for (File file : fileFolder.listFiles()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                Account account = (Account) objectInputStream.readObject();
                accounts.add(account);
            }
        }
        return accounts;
    }

    public Account readAccountFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Account) objectInputStream.readObject();
        }
    }
}
