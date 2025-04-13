package com.example.motorentmobile.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatMoneyUtil {
    public static String format(double amount) {
        return NumberFormat.getInstance(new Locale("vi", "VN")).format(amount) + " đ";
    }
}
