package com.express.freight.user.mapper;

import com.express.freight.user.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsUserEntityByUser_id(String userId);
}
