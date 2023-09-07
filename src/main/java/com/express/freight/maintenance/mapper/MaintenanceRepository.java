package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Long> {

    MaintenanceEntity getMaintenanceEntitiesByUserIdOrderByCreatedAtDesc(Long userId);

}
