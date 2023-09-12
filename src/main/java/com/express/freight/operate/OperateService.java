package com.express.freight.operate;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.maintenance.dto.MaintenanceEntity;
import com.express.freight.maintenance.mapper.MaintenanceMapper;
import com.express.freight.operate.dto.OperateDto;
import com.express.freight.operate.dto.OperateEntity;
import com.express.freight.operate.mapper.OperateMapper;
import com.express.freight.operate.mapper.OperateRepository;
import com.express.freight.operate.mapper.OperateRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OperateService {

    private final OperateRepository operateRepository;
    private final OperateRepositoryCustom operateRepositoryCustom;

    public OperateService(OperateRepository operateRepository, OperateRepositoryCustom operateRepositoryCustom) {
        this.operateRepository = operateRepository;
        this.operateRepositoryCustom = operateRepositoryCustom;
    }

    public OperateDto postOperate(OperateDto operateDto) {

        OperateEntity entity = OperateMapper.mapper.toEntity(operateDto);
        entity = operateRepository.save(entity);

        return OperateMapper.mapper.toDto(entity);
    }

    public PagingDto<OperateDto> getOperateList(String userId, Pageable pageable, LocalDate date) {

        return operateRepositoryCustom.getOperateList(userId, pageable, date);

    }

    public OperateDto getOperateDetail(Long id){

        OperateEntity operateEntity = operateRepository.getOperateEntityById(id);
        return OperateMapper.mapper.toDto(operateEntity);
    }

    public OperateDto putOperate(OperateDto operateDto) {

        Optional<OperateEntity> entity = operateRepository.findById(operateDto.getId());

        if (entity.isPresent()) {
            OperateEntity target = entity.get();
            target.updateOperate(operateDto);
            operateRepository.save(target);

            return OperateMapper.mapper.toDto(target);
        }
        return null;
    }

    public void deleteOperate(Long id) {
        Optional<OperateEntity> entity = operateRepository.findById(id);

        if (entity.isPresent()) {
            OperateEntity target = entity.get();
            target.setDelYn('Y');
            operateRepository.save(target);
        }
    }

}
