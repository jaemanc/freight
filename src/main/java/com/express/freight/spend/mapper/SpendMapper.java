package com.express.freight.spend.mapper;

import com.express.freight.common.EntityMapper;
import com.express.freight.spend.dto.SpendDto;
import com.express.freight.spend.dto.SpendEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpendMapper extends EntityMapper<SpendDto, SpendEntity> {

    SpendMapper mapper = Mappers.getMapper(SpendMapper.class);
}
