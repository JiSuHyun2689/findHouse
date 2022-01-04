package org.suhyun.findhouse.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.OptionDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.dto.StructureDTO;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.entity.Structure;
import org.suhyun.findhouse.repository.StructureRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class StructureServiceImpl implements StructureService{

    private final StructureRepository repository;


    @Override
    public Long register(StructureDTO dto) {

        log.info("Structure DTO register------------------------------------------------------------");
        log.info(dto);

        Structure entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getStructureNum();
    }


    @Override
    public PageResultDTO<StructureDTO, Structure> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        Page<Structure> result = repository.findAll(pageable);

        Function<Structure, StructureDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public StructureDTO read(Long houseNum) {

        Optional<Structure> result = repository.findByHouse(houseNum);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }


    @Override
    public void modify(StructureDTO dto) {

        Optional<Structure> result = repository.findByHouse(dto.getHouseNum());

        if(result.isPresent()){

            Structure entity = result.get();

            entity.chageStructures(dto.getRoom(), dto.getToilet(), dto.getLivingRoom(), dto.getVeranda());

            repository.save(entity);
        }
    }



    @Override
    public void remove(Long houseNum) {
        repository.deleteByHouse(houseNum);
    }
}
