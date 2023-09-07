package com.express.freight.user;

import com.express.freight.user.dto.UserDto;
import com.express.freight.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
@Tag(name="User", description = "유저 API")
public class UserController {

    private final Logger log_error = LogManager.getLogger("com.applog");

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    JWTUtil jwtUtil = new JWTUtil();


    @Tag(name="User")
    @Operation(summary = "user register & login", description = "회원 가입 및 로그인")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> userRegist(
            @RequestBody UserDto userDto
    ){
        try {
            if (ObjectUtils.isEmpty(userDto)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            userDto = userService.userRegist(userDto);
            String jwt = jwtUtil.createToken(userDto);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", jwt);
            return new ResponseEntity<UserDto>(userDto, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name="User")
    @Operation(summary = "Non-membership registration", description = "비회원 로그인")
    @PostMapping("/non-member-registration")
    public ResponseEntity<UserDto> nonMemberLogin(){

        try {
            UserDto userDto = userService.nonMemberLogin();

            String jwt = jwtUtil.createToken(userDto);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", jwt);

            return new ResponseEntity<UserDto>(userDto, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
