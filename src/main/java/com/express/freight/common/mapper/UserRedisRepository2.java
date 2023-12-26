package com.express.freight.common.mapper;

import com.express.freight.common.dto.RedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository2 extends CrudRepository<RedisEntity, String> {
}
