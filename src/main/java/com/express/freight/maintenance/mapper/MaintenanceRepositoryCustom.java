package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.QMaintenanceEntity;
import com.express.freight.maintenance.mapper.MaintenanceRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MaintenanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QMaintenanceEntity qMaintenanceEntity = QMaintenanceEntity.maintenanceEntity;




}
