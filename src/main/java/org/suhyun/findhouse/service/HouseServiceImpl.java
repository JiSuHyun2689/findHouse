package org.suhyun.findhouse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.dto.PageResultDTO;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.repository.HouseRepository;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    public Long register(HouseDTO dto) {

        log.info("DTO--------------------------");
        log.info(dto);

        House entity = dtoToEntity(dto);
        log.info(entity);

        houseRepository.save(entity);
        return entity.getHouseNum();
    }

    @Override
    public PageResultDTO<HouseDTO, House> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        Page<House> result = houseRepository.findAll(pageable);

        Function<House, HouseDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }


}
