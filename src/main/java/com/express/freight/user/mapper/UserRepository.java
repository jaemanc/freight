package com.express.freight.user.mapper;

import com.express.freight.user.dto.UserDto;
import com.express.freight.user.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Boolean existsUserEntityByUserId(String userId);
    Boolean existsUserEntityByUserIdAndName(String userId, String name);
    Boolean existsUserEntityByNameAndEmail(String name, String email);
    @Query("SELECT CASE WHEN (COUNT(u) > 0) THEN true ELSE false END from UserEntity u WHERE u.userId = :user_id OR u.email = :email OR u.contact = :contact")
    boolean existsUserByUserIdOrEmailOrContact(@Param("user_id") String user_id, @Param("email") String email ,@Param("contact") String contact);
}
