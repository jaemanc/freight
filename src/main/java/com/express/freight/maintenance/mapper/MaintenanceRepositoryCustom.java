package com.express.freight.maintenance.mapper;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.maintenance.dto.QMaintenanceEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MaintenanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QMaintenanceEntity qMaintenanceEntity = QMaintenanceEntity.maintenanceEntity;


    public PagingDto<MaintenanceDto> getMaintenanceList(String userId, Pageable pageable, LocalDate date) {
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
                    .and(qMaintenanceEntity.delYn.eq('N'))
                )
                .orderBy(qMaintenanceEntity.maintenanceDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(qMaintenanceEntity.count())
                .from(qMaintenanceEntity)
                .where(
                        qMaintenanceEntity.userId.eq(userId)
                        .and(qMaintenanceEntity.maintenanceDate.between(firstDayOfMonth,lastDayOfMonth))
                        .and(qMaintenanceEntity.delYn.eq('N'))
                        )
                .fetchOne();

        return new PagingDto<>(maintenanceDtoList, totalCount);
    }

}
