package com.express.freight.maintenance;

import com.express.freight.maintenance.dto.MaintenanceDto;
import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.maintenance.mapper.MaintenanceMapper;
import com.express.freight.maintenance.mapper.MaintenanceRepository;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository){
        this.maintenanceRepository = maintenanceRepository;
    }

    public MaintenanceDto postMaintenance(MaintenanceDto maintenanceDto){

        MaintenanceEntity maintenanceEntity = MaintenanceMapper.mapper.toEntity(maintenanceDto);

        maintenanceEntity = maintenanceRepository.save(maintenanceEntity);

        return MaintenanceMapper.mapper.toDto(maintenanceEntity);
    }

}