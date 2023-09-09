package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.maintenance.dto.QMaintenanceEntity;
import com.express.freight.util.CalendarUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static com.express.freight.maintenance.dto.QMaintenanceEntity.maintenanceEntity;

@Repository
@RequiredArgsConstructor
public class MaintenanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final CalendarUtil calendarUtil;

    private final QMaintenanceEntity qMaintenanceEntity = QMaintenanceEntity.maintenanceEntity;


    public List<MaintenanceDto> getMaintenanceList(String userId, Pageable pageable, LocalDate date) {

//        int year = calendarUtil.getYear(date);
//        int month = calendarUtil.getMonth(date);

        YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

        List<MaintenanceDto> maintenanceDtoList = queryFactory
                .select(Projections.fields(MaintenanceDto.class
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
                    qMaintenanceEntity.maintenanceDate.between(firstDayOfMonth,lastDayOfMonth)
                            .and(qMaintenanceEntity.userId.eq(userId))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qMaintenanceEntity.createdAt.desc())
                .fetch();

        return maintenanceDtoList;
    }

}
