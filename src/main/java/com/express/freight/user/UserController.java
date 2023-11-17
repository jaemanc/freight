package com.express.freight.user;

import com.express.freight.user.dto.UserDto;
import com.express.freight.user.dto.UserLoginDto;
import com.express.freight.util.JWTUtil;
import com.mysql.cj.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @Operation(summary = "Log in to a previously subscribed member AND api for device change users ")
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(
            @RequestBody UserLoginDto userLoginDto,
            HttpServletRequest request
    ){
        try {

            if (ObjectUtils.isEmpty(userLoginDto) || StringUtils.isNullOrEmpty(request.getHeader("Authorization"))){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            userLoginDto.setJwt(request.getHeader("Authorization"));
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization",request.getHeader("Authorization"));

            if (!userService.isValidUser(userLoginDto)) {
                return new ResponseEntity<>(null, httpHeaders, HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
            // 동일 아이디 중복 체크
            if (userService.checkDuplicateUser(userDto)) {
                return new ResponseEntity<UserDto>(userDto,  new HttpHeaders(), HttpStatus.CONFLICT);
            }

            userDto = userService.userRegist(userDto);
            String jwt = jwtUtil.createToken(userDto);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", jwt);
            return new ResponseEntity<UserDto>(userDto, httpHeaders, HttpStatus.CREATED);
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

            return new ResponseEntity<UserDto>(userDto, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

//    @Tag(name="User")
//    @Operation(summary = "Update User Info ", description = "사용자 정보 수정")
//    @PutMapping("/{user_id}")
//    public ResponseEntity<UserDto> patchUser(
//            @PathVariable String user_id
//            ,@RequestBody UserDto userDto
//    ){
//        try {
//            userService.isUser(user_id);
//
//            String jwt = jwtUtil.createToken(userDto);
//
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.add("Authorization", jwt);
//
//            return new ResponseEntity<UserDto>(userDto, httpHeaders, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

}
