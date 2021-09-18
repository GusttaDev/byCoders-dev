package com.bycoders.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static LocalDate stringToLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, formatter);
    }

    public static LocalTime formatHour(String hour) {

        int hours = Integer.parseInt(hour.substring(0, 2));
        int minutes = Integer.parseInt(hour.substring(2, 4));
        int seconds = Integer.parseInt(hour.substring(4, 6));

        return LocalTime.of(hours, minutes, seconds);
    }

}
