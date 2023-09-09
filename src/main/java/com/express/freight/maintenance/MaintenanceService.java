package com.express.freight.maintenance;

import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.maintenance.mapper.MaintenanceMapper;
import com.express.freight.maintenance.mapper.MaintenanceRepository;
import com.express.freight.maintenance.mapper.MaintenanceRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceRepositoryCustom maintenanceRepositoryCustom;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, MaintenanceRepositoryCustom maintenanceRepositoryCustom){
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceRepositoryCustom = maintenanceRepositoryCustom;
    }

    public MaintenanceDto postMaintenance(MaintenanceDto maintenanceDto){

        MaintenanceEntity maintenanceEntity = MaintenanceMapper.mapper.toEntity(maintenanceDto);

        maintenanceEntity = maintenanceRepository.save(maintenanceEntity);

        return MaintenanceMapper.mapper.toDto(maintenanceEntity);
    }

    public List<MaintenanceDto> getMaintenanaceList(String userId, Pageable pageable, LocalDate date){

        List<MaintenanceDto> maintenanceDtoList = maintenanceRepositoryCustom.getMaintenanceList(userId, pageable, date);

        // List<MaintenanceDto> maintenanceDtoList = MaintenanceMapper.mapper.toDtoList(maintenanceEntityList);

        return maintenanceDtoList;
    }


}