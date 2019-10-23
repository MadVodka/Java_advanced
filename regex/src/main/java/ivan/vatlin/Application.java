package ivan.vatlin;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        TxtFileService txtFileService = new TxtFileService();
        try {
            String initialFilePath = "regex/src/main/resources/InitialTextFile.txt";
            String textOfInitialFile = txtFileService.readFile(initialFilePath);

            String regExPattern = "\\+\\d\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}";
            String newText = PhoneNumberFormatter.leaveOnlyDigits(textOfInitialFile, regExPattern);

            String newFilePath = "regex/src/main/resources/NewTextFile.txt";
            txtFileService.writeFile(newText, newFilePath);

            System.out.println("Преобразование телефонных номеров выполнено");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла");
        }
    }
}
