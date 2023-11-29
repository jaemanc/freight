package com.express.freight.user;

import com.express.freight.user.dto.UserDto;
import com.express.freight.user.dto.UserEntity;
import com.express.freight.user.dto.UserLoginDto;
import com.express.freight.user.mapper.UserMapper;
import com.express.freight.user.mapper.UserRepository;
import com.express.freight.user.mapper.UserRepositoryCustom;
import com.express.freight.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;

    public UserService(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom){
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    public UserDto userRegist(UserDto userDto) throws Exception {

        UserEntity userEntity = UserMapper.mapper.toEntity(userDto);

        userEntity = userRepository.save(userEntity);

        userDto = UserMapper.mapper.toDto(userEntity);

        return userDto;
    }
    public UserDto nonMemberLogin() throws Exception {
        /* Todo
            userId = UUID 생성
            name = 게스트_uuid
            contact = 01
            email = guest@gmail.com
         */
        String uuid = UUID.randomUUID().toString();
        UserEntity userEntity = UserEntity.builder()
                            .userId(uuid)
                            .name("geust_"+uuid)
                            .contact("contact_"+uuid)
                            .email("geust@gmail.com")
                            .build();

        userRepository.save(userEntity);

        return UserMapper.mapper.toDto(userEntity);
    }

    public boolean isUser(String userId) throws Exception {

        return userRepository.existsUserEntityByUserId(userId);
    }

    public boolean isValidUser(UserLoginDto userLoginDto) throws Exception {
        String userName = JWTUtil.getUserName(userLoginDto.getJwt());

        if (!userName.equals(userLoginDto.getName())) {
            return false;
        }

        if (userRepository.existsUserEntityByNameAndEmail(userName, userLoginDto.getEmail())) {
            return true;
        }

        return false;
    }

    public boolean checkDuplicateUser(UserDto userDto) throws Exception {
        return userRepository.existsUserByUserIdOrEmailOrContact(userDto.getUserId(), userDto.getEmail(), userDto.getContact());
    }

}
