package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.maintenance.dto.QMaintenanceEntity;
import com.express.freight.maintenance.mapper.MaintenanceRepository;
import com.express.freight.util.CalendarUtil;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MaintenanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final CalendarUtil calendarUtil;

    private final QMaintenanceEntity qMaintenanceEntity = QMaintenanceEntity.maintenanceEntity;


    public List<MaintenanceEntity> getMaintenanceList(String userId, Pageable pageable, Date date) {

        int year = calendarUtil.getYear(date);
        int month = calendarUtil.getMonth(date);

        return queryFactory
                .select(Projections.fields(MaintenanceEntity.class))
                .from(qMaintenanceEntity)
                .where(
                        qMaintenanceEntity.maintenanceDate.year().eq(year)
                        .and(qMaintenanceEntity.maintenanceDate.month().eq(month))
                                .and(qMaintenanceEntity.userId.eq(userId))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qMaintenanceEntity.createdAt.desc())
                .fetch();
    }

}
