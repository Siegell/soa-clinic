package by.siegell.soa.clinic.util;

import java.time.LocalTime;

public class LocalTimeUtil {
    public static boolean isBetween(LocalTime time, LocalTime start, LocalTime end) {
        return time.isAfter(start) && time.isBefore(end);
    }
}
