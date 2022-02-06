package org.suhyun.findhouse.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
import org.suhyun.findhouse.entity.QHouse;
import org.suhyun.findhouse.repository.HouseRepository;


import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;


    @Override
    public Long register(HouseDTO dto) {

        log.info("House DTO--------------------------");
        log.info(dto);

        House entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getHouseNum();
    }


    @Override
    public PageResultDTO<HouseDTO, House> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<House> result = repository.findAll(booleanBuilder, pageable);

        Function<House, HouseDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public HouseDTO read(Long houseNum) {

        Optional<House> result = repository.findById(houseNum);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }


    @Override
    public void modify(HouseDTO dto) {

        Optional<House> result = repository.findById(dto.getHouseNum());

        if(result.isPresent()){

            House entity = result.get();

            entity.changeStatus(dto.getStatus());
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            entity.changeInfo(dto.getAddress(),
                    dto.getContractType(),
                    dto.getMinTerm(),
                    dto.getBrokerage(),
                    dto.getMoveInDate(),
                    dto.getCompletionDate(),
                    dto.isLoan(),
                    dto.isElevator(),
                    dto.isPet(),
                    dto.isParking());

            repository.save(entity);
        }
    }


    @Override
    public void remove(Long houseNum) {
        repository.deleteById(houseNum);
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO){

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QHouse qHouse = QHouse.house;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qHouse.houseNum.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("t"))
            conditionBuilder.or(qHouse.title.contains(keyword));

        if(type.contains("c"))
            conditionBuilder.or(qHouse.content.contains(keyword));

        if(type.contains("w"))
            conditionBuilder.or(qHouse.id.contains(keyword));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
