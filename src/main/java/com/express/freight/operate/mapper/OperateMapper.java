package com.express.freight.operate.mapper;

import com.express.freight.common.EntityMapper;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.operate.dto.OperateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperateMapper extends EntityMapper<OperateDto, OperateEntity> {

    OperateMapper mapper = Mappers.getMapper(OperateMapper.class);
}
