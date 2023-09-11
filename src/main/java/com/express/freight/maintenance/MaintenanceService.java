package com.express.freight.maintenance;

import com.express.freight.common.dto.PagingDto;
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
import java.util.Optional;

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

    public PagingDto<MaintenanceDto> getMaintenanceList(String userId, Pageable pageable, LocalDate date){

        PagingDto<MaintenanceDto> maintenanceDtoList = maintenanceRepositoryCustom.getMaintenanceList(userId, pageable, date);

        return maintenanceDtoList;
    }

    public MaintenanceDto getMaintenanceDetail(String userId, Long id){

        MaintenanceEntity entity = maintenanceRepository.getMaintenanceEntityByUserIdAndId(userId, id);

        return MaintenanceMapper.mapper.toDto(entity);
    }

    public MaintenanceDto putMaintenanace(MaintenanceDto maintenanceDto) {

        Optional<MaintenanceEntity> entity = maintenanceRepository.findById(maintenanceDto.getId());

        if (entity.isPresent()) {
            MaintenanceEntity target = entity.get();
            target.updateMaintenance(maintenanceDto);
            maintenanceRepository.save(target);

            return MaintenanceMapper.mapper.toDto(target);
        }
        return null;
    }

    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }

}