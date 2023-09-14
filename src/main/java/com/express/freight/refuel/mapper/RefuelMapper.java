package com.express.freight.refuel.mapper;

import com.express.freight.common.EntityMapper;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.operate.dto.OperateEntity;
import com.express.freight.operate.mapper.OperateMapper;
import com.express.freight.refuel.dto.RefuelDto;
import com.express.freight.refuel.dto.RefuelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RefuelMapper extends EntityMapper<RefuelDto, RefuelEntity> {

    RefuelMapper mapper = Mappers.getMapper(RefuelMapper.class);
}
