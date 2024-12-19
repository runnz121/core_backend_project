package kitten.core.coredomain.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final String YYMMDD_STRING = "yyMMdd";

    public static String toString(LocalDateTime dateTime,
                                  String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
