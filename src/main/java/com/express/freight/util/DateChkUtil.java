package com.express.freight.util;

import com.express.freight.common.dto.Category;
import org.springframework.util.StringUtils;

public class DateChkUtil {

    public static Category dateFormatChecker(String date) throws Exception {
        if (!StringUtils.hasText(date)) {
            throw new IllegalArgumentException("Date should never be empty.");
        }

        String yearRegex = "^\\d{4}$";
        String yearMonthRegex = "^\\d{4}-(0[1-9]|1[0-2])$";

        if (date.matches(yearMonthRegex)) {
            return Category.YEAR_MONTH;
        }
        if (date.matches(yearRegex)) {
            return Category.YEAR;
        }

        throw new Exception("not valid date");
    }

}
