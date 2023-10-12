package com.express.freight.util;

import com.express.freight.operate.dto.OperateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ExceptionHandler {


    @GetMapping("/error/400")
    public ResponseEntity<?> return400(
            HttpServletRequest request
    ){
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("msg", "not found jwt");

        return ResponseEntity.badRequest().body(responseMap);
    }

    @GetMapping("/error/401")
    public ResponseEntity<?> return401(
            HttpServletRequest request
    ){
        System.out.println("ERROR !!!! 401 ");
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/error/404")
    public ResponseEntity<?> return404(
            HttpServletRequest request
    ){
        System.out.println("ERROR !!!! 404 ");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
