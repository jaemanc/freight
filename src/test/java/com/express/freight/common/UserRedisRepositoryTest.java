package com.express.freight.common;

import com.express.freight.TestConfig;
import com.express.freight.common.dto.RedisEntity;
import com.express.freight.common.mapper.UserRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({TestConfig.class})
public class UserRedisRepositoryTest {

    @Autowired
    private UserRedisRepository userRedisRepositoryTest;
    @Test
    void test() {
        RedisEntity redisEntity = RedisEntity
                .builder()
                .userId("userTestId1")
                .email("emial")
                .name("name22")
                .extra("extra11")
                .contact("contact!")
                .build();



        userRedisRepositoryTest.save(redisEntity);

        userRedisRepositoryTest.findById(redisEntity.getUserId());

        userRedisRepositoryTest.count();

        userRedisRepositoryTest.delete(redisEntity);

    }

}