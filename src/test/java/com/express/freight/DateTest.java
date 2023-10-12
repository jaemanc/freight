package com.express.freight;

import java.time.YearMonth;

public class DateTest {

    public static void main(String[] args) {
        //YearMonth yearMonth = YearMonth.from("2023-09");
        String yearRegex = "^\\d{4}$";
        String yearMonthRegex = "^\\d{4}-(0[1-9]|1[0-2])$";

        String test = "2023-09";

        System.out.println(test.matches(yearRegex) + " / yearMonth " + test.matches(yearMonthRegex));
    }

}
