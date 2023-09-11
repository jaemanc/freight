package com.express.freight.maintenance.mapper;

import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaintenanceMapper extends EntityMapper<MaintenanceDto, MaintenanceEntity> {

    MaintenanceMapper mapper = Mappers.getMapper(MaintenanceMapper.class);

}
