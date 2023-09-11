package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.MaintenanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceEntity, Object> {

    MaintenanceEntity getMaintenanceEntitiesByUserIdOrderByCreatedAtDesc(Long id);

    MaintenanceEntity getMaintenanceEntityByUserIdAndId(String userId, Long id);

}
