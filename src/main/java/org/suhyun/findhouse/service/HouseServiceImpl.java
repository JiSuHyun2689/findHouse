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
import org.suhyun.findhouse.entity.*;
import org.suhyun.findhouse.repository.HouseImageRepository;
import org.suhyun.findhouse.repository.HouseRepository;


import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;

    private final HouseImageRepository houseImageRepository;


    @Override
    @Transactional
    public Long register(HouseDTO dto) {

        log.info("House DTO--------------------------");

        log.info(dto);

        Map<String, Object> entityMap =dtoToEntity(dto);

        House house = (House) entityMap.get("house");

        List<HouseImage> houseImageList = (List<HouseImage>)entityMap.get("imgList");

        repository.save(house);

        if(houseImageList != null) {
            houseImageList.forEach(houseImage -> {
                houseImageRepository.save(houseImage);
            });
        }

        return house.getHouseNum();
    }


    @Override
    public PageResultDTO<HouseDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Object[]> result = repository.getListPage(pageable);

        Function<Object[], HouseDTO> fn = (arr -> entityToDto(
                (House) arr[0],
                Arrays.asList((HouseImage)arr[1]),
                (Double) arr[2],
                (Long)arr[3],
                (Option) arr[4],
                (Price) arr[5],
                (Structure) arr[6],
                (Cost)arr[7])
        );

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public HouseDTO read(Long houseNum) {

        List<Object[]> result = repository.getHouseWithAll(houseNum);

        House house = (House) result.get(0)[0];

        List<HouseImage> houseImageList = new ArrayList<>();

        result.forEach(arr -> {
            HouseImage houseImage = (HouseImage) arr[1];
            houseImageList.add(houseImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];
        Option option = (Option) result.get(0)[4];
        Price price = (Price) result.get(0)[5];
        Structure structure = (Structure) result.get(0)[6];
        Cost cost = (Cost) result.get(0)[7];


        return entityToDto(house, houseImageList, avg, reviewCnt, option, price, structure, cost);
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
    @Transactional
    public void remove(Long houseNum) {

        houseImageRepository.deleteByHouse(houseNum);

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
