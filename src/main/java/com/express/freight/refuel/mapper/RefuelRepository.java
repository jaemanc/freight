package com.express.freight.refuel.mapper;

import com.express.freight.operate.dto.OperateEntity;
import com.express.freight.refuel.dto.RefuelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefuelRepository extends JpaRepository<RefuelEntity, Object> {

    RefuelEntity getRefuelEntityByid(Long id);

}
