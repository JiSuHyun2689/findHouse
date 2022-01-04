package org.suhyun.findhouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.CostDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.Structure;
import org.suhyun.findhouse.repository.CostRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class CostServiceImpl implements CostService {

    private final CostRepository repository;


    @Override
    public Long register(CostDTO dto) {

        log.info("Cost DTO register------------------------------------------------------------");
        log.info(dto);

        Cost entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getCostNum();
    }


    @Override
    public PageResultDTO<CostDTO, Cost> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        Page<Cost> result = repository.findAll(pageable);

        Function<Cost, CostDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public CostDTO read(Long houseNum) {

        Optional<Cost> result = repository.findByHouse(houseNum);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }


    @Override
    public void modify(CostDTO dto) {

        Optional<Cost> result = repository.findByHouse(dto.getHouseNum());

        if(result.isPresent()){

            Cost entity = result.get();

            entity.changeContent(dto.getCostContent());

            entity.changeCosts(dto.getTotalCost(), dto.isElectricity(), dto.isGas(), dto.isWater(), dto.isCostTv(), dto.isInternet(), dto.isCostParking(), dto.isEtc());

            repository.save(entity);
        }
    }


    @Override
    public void remove(Long houseNum) {
        repository.deleteByHouse(houseNum);
    }
}
