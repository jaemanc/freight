package com.express.freight.common.mapper;

import com.express.freight.common.dto.RedisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

public interface UserRedisRepository extends CrudRepository<RedisEntity, String> {
}
