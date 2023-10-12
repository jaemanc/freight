package com.express.freight.common.mapper;

import com.express.freight.common.dto.Category;
import com.express.freight.common.dto.PagingDto;
import com.express.freight.maintenance.dto.QMaintenanceEntity;
import com.express.freight.operate.dto.QOperateEntity;
import com.express.freight.refuel.dto.QRefuelEntity;
import com.express.freight.spend.dto.QSpendEntity;
import com.express.freight.util.DateChkUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommonRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QOperateEntity qOperateEntity = QOperateEntity.operateEntity;
    private final QRefuelEntity qRefuelEntity = QRefuelEntity.refuelEntity;
    private final QSpendEntity qSpendEntity = QSpendEntity.spendEntity;
    private final QMaintenanceEntity qMaintenanceEntity = QMaintenanceEntity.maintenanceEntity;


    public <T> PagingDto<T> getExcelData(String userId, String category, String date, Class<T> dtoClass) throws Exception{
        DateTimeFormatter YearMonthformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate firstDayOfYearMonth = null;
        LocalDate lastDayOfYearMonth = null;

        Category dateFormatCategory = DateChkUtil.dateFormatChecker(date);

        if (dateFormatCategory.equals(Category.YEAR)) {
            firstDayOfYearMonth = LocalDate.parse(String.format("%d-01-01",Integer.parseInt(date)));
            lastDayOfYearMonth = LocalDate.parse(String.format("%d-12-31",Integer.parseInt(date)));
        }

        if (dateFormatCategory.equals(Category.YEAR_MONTH)) {
            firstDayOfYearMonth = YearMonth.from(LocalDate.parse(date+"-01", YearMonthformatter)).atDay(1);
            lastDayOfYearMonth = YearMonth.from(LocalDate.parse(date+"-01", YearMonthformatter)).atEndOfMonth();
        }

        switch (Category.valueOf(category.toUpperCase())) {
            case OPERATE:
                List<T> operateDtoList = queryFactory
                        .select(Projections.fields(dtoClass
                                ,qOperateEntity.id
                                ,qOperateEntity.createdAt
                                ,qOperateEntity.loadingDate
                                ,qOperateEntity.loadingRatio
                                ,qOperateEntity.loadingPlace
                                ,qOperateEntity.delYn
                                ,qOperateEntity.transportationCosts
                                ,qOperateEntity.transportationDate
                                ,qOperateEntity.transportationType
                                ,qOperateEntity.unitCost
                                ,qOperateEntity.unloadingDate
                                ,qOperateEntity.unloadingPlace
                                ,qOperateEntity.userId
                        ))
                        .from(qOperateEntity)
                        .where(
                                qOperateEntity.delYn.eq('N')
                                        .and(qOperateEntity.loadingDate.between(firstDayOfYearMonth, lastDayOfYearMonth))
                                        .and(qOperateEntity.userId.eq(userId))
                        )
                        .orderBy(qOperateEntity.loadingDate.desc())
                        .fetch();
                return new PagingDto<>(operateDtoList,0L,0L);
            case SPEND:
                List<T> spendDtoList = queryFactory
                        .select(Projections.fields(dtoClass
                                ,qSpendEntity.id
                                ,qSpendEntity.createdAt
                                ,qSpendEntity.userId
                                ,qSpendEntity.extra
                                ,qSpendEntity.paymentDate
                                ,qSpendEntity.paymentDetail
                                ,qSpendEntity.price
                        ))
                        .from(qSpendEntity)
                        .where(
                                qSpendEntity.delYn.eq('N')
                                        .and(qSpendEntity.paymentDate.between(firstDayOfYearMonth, lastDayOfYearMonth))
                                        .and(qSpendEntity.userId.eq(userId))
                        )
                        .orderBy(qSpendEntity.paymentDate.desc())
                        .fetch();
                return new PagingDto<>(spendDtoList,0L,0L);

            case MAINTENANCE:
                List<T> maintenanceDtoList = queryFactory
                        .select(Projections.fields(dtoClass
                                ,qMaintenanceEntity.id
                                ,qMaintenanceEntity.userId
                                ,qMaintenanceEntity.maintenanceDate
                                ,qMaintenanceEntity.maintenanceHistory
                                ,qMaintenanceEntity.maintenanceShop
                                ,qMaintenanceEntity.createdAt
                                ,qMaintenanceEntity.delYn
                                ,qMaintenanceEntity.extra
                                ,qMaintenanceEntity.price
                        ))
                        .from(qMaintenanceEntity)
                        .where(
                                qMaintenanceEntity.maintenanceDate.between(firstDayOfYearMonth,lastDayOfYearMonth)
                                        .and(qMaintenanceEntity.userId.eq(userId))
                                        .and(qMaintenanceEntity.delYn.eq('N'))
                        )
                        .orderBy(qMaintenanceEntity.maintenanceDate.desc())
                        .fetch();
                return new PagingDto<>(maintenanceDtoList,0L,0L);

            case REFUEL:
                List<T> refuelDtoList = queryFactory
                        .select(Projections.fields(dtoClass
                                ,qRefuelEntity.id
                                ,qRefuelEntity.userId
                                ,qRefuelEntity.refuelingDate
                                ,qRefuelEntity.delYn
                                ,qRefuelEntity.price
                                ,qRefuelEntity.extra
                        ))
                        .from(qRefuelEntity)
                        .where(
                                qRefuelEntity.delYn.eq('N')
                                        .and(qRefuelEntity.refuelingDate.between(firstDayOfYearMonth, lastDayOfYearMonth))
                                        .and(qRefuelEntity.userId.eq(userId))
                        )
                        .orderBy(qRefuelEntity.refuelingDate.desc())
                        .fetch();
                return new PagingDto<>(refuelDtoList,0L,0L);

            default:
                return new PagingDto<>(new ArrayList<>(),0L,0L);
        }
    }

}
