package com.express.freight.common;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.common.dto.RedisEntity;
import com.express.freight.common.mapper.UserRedisRepository2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/test")
@Tag(name="Test", description = "TEST API")
public class TestController {

    private final UserRedisRepository2 userRedisRepository2;
    public TestController(UserRedisRepository2 userRedisRepository2) {
        this.userRedisRepository2 = userRedisRepository2;
    }

    @Tag(name="Redis")
    @Operation(summary = "Redis test API", description = "레디스 테스트")
    @GetMapping("/{searchword}")
    public ResponseEntity<?> redisTestApi(
            HttpServletRequest request,
            @RequestParam(required = true) String searchword
    ) {
        try{

            RedisEntity redisEntity = RedisEntity
                    .builder()
                    .userId("userTestId1")
                    .email("emial")
                    .name("name22")
                    .extra("extra11")
                    .contact("contact!")
                    .build();

            userRedisRepository2.save(redisEntity);

            userRedisRepository2.findById(redisEntity.getUserId());

            userRedisRepository2.count();

            userRedisRepository2.delete(redisEntity);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
