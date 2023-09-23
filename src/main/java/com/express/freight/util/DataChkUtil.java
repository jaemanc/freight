package com.express.freight.util;

import com.express.freight.common.dto.PagingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

public class DataChkUtil {
    public static <T> boolean isEmpty(T list) {

        boolean flag = false;

        if (list instanceof PagingDto<?> && ((PagingDto<?>) list).getData().size() <= 0)  {
            flag = true;
        } else if (ObjectUtils.isEmpty(list)) {
            flag = true;
        }

        return flag;
    }
}
