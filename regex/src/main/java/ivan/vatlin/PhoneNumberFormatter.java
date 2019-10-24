package ivan.vatlin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberFormatter {
    public static String leaveOnlyDigits(String text, String phoneNumberPattern) {
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String phoneNumber = matcher.group();
            String newPhoneNumber = phoneNumber.replaceAll("\\D", "");
            text = text.replace(phoneNumber, newPhoneNumber);
        }
        return text;
    }
}
