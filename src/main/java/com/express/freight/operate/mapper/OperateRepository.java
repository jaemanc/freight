package com.express.freight.operate.mapper;

import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.operate.dto.OperateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OperateRepository extends JpaRepository<OperateEntity, Object> {

    OperateEntity getOperateEntityById(Long id);

}
