package ivan.vatlin;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TxtFileService {
    public String readFile(String path) throws IOException {
        try (InputStream inputStream = new FileInputStream(path)) {
            int symbol;
            StringBuilder stringBuilder = new StringBuilder();
            while ((symbol = inputStream.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
            return stringBuilder.toString();
        }
    }

    public void writeFile(String text, String path) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(path)) {
            byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
            outputStream.write(textBytes);
        }
    }
}
