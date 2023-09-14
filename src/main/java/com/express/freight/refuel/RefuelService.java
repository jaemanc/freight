package com.express.freight.refuel;

import com.express.freight.common.dto.PagingDto;
import com.express.freight.operate.dto.OperateEntity;
import com.express.freight.operate.mapper.OperateMapper;
import com.express.freight.refuel.dto.RefuelDto;
import com.express.freight.refuel.dto.RefuelEntity;
import com.express.freight.refuel.mapper.RefuelMapper;
import com.express.freight.refuel.mapper.RefuelRepository;
import com.express.freight.refuel.mapper.RefuelRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RefuelService {

    private final RefuelRepository refuelRepository;
    private final RefuelRepositoryCustom refuelRepositoryCustom;

    public RefuelService(RefuelRepository refuelRepository, RefuelRepositoryCustom refuelRepositoryCustom) {
        this.refuelRepository = refuelRepository;
        this.refuelRepositoryCustom = refuelRepositoryCustom;
    }

    public RefuelDto postRefuel(RefuelDto refuelDto) {
        RefuelEntity refuelEntity = RefuelMapper.mapper.toEntity(refuelDto);
        refuelEntity = refuelRepository.save(refuelEntity);
        return RefuelMapper.mapper.toDto(refuelEntity);
    }

    public PagingDto<RefuelDto> getRefuelList(String userId, Pageable pageable, LocalDate date) {

        return refuelRepositoryCustom.getRefuelList(userId, pageable, date);

    }

    public RefuelDto getRefuelingDetail(Long id){
        RefuelEntity refuelEntity = refuelRepository.getRefuelEntityByid(id);
        return RefuelMapper.mapper.toDto(refuelEntity);
    }

    public RefuelDto putRefueling(RefuelDto refuelDto) {
        Optional<RefuelEntity> entity = refuelRepository.findById(refuelDto.getId());

        if (entity.isPresent()) {
            RefuelEntity target = entity.get();
            target.updateRefueling(refuelDto);
            refuelRepository.save(target);

            return RefuelMapper.mapper.toDto(target);
        }
        return null;
    }

    public void deleteRefueling(Long id) {
        Optional<RefuelEntity> entity = refuelRepository.findById(id);

        if (entity.isPresent()) {
            RefuelEntity target = entity.get();
            target.setDelYn('Y');
            refuelRepository.save(target);
        }
    }



}
