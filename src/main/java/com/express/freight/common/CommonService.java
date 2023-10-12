package com.express.freight.common;

import com.express.freight.common.dto.Category;
import com.express.freight.common.dto.PagingDto;
import com.express.freight.common.mapper.CommonRepositoryCustom;
import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.refuel.dto.RefuelDto;
import com.express.freight.spend.dto.SpendDto;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.express.freight.common.dto.Category.OPERATE;

@Service
public class CommonService {

    private final CommonRepositoryCustom commonRepositoryCustom;

    public CommonService(CommonRepositoryCustom commonRepositoryCustom) {
        this.commonRepositoryCustom = commonRepositoryCustom;
    }

    public <T> PagingDto<?> getExcelData(String userId, String category, String date) throws Exception {

        PagingDto<?> excelData = new PagingDto<>();

        switch (Category.valueOf(category.toUpperCase())) {

            case OPERATE:
                return commonRepositoryCustom.getExcelData(userId, category, date, OperateDto.class);

            case SPEND:
                return commonRepositoryCustom.getExcelData(userId, category, date, SpendDto.class);

            case MAINTENANCE:
                return commonRepositoryCustom.getExcelData(userId, category, date, MaintenanceDto.class);

            case REFUEL:
                return commonRepositoryCustom.getExcelData(userId, category, date, RefuelDto.class);

            default:
                return excelData;
        }
    }


}
