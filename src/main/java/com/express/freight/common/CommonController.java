package com.express.freight.common;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.util.DataChkUtil;
import com.express.freight.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/common")
@Tag(name="Maintenance", description = "정비 API")
public class CommonController {


    @Tag(name="Search")
    @Operation(summary = "Search all Data", description = "통합 검색")
    @GetMapping("/{searchword}")
    public ResponseEntity<PagingDto<?>> getIntegratedSearch(
            HttpServletRequest request,
            @RequestParam(required = true) String searchword
    ) {
        try{
            /*Todo

             elastic search 사용?
             사용자 3명인데 통합검색있어야하냐..?

            */
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
