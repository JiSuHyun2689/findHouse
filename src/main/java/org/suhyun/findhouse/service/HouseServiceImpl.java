package org.suhyun.findhouse.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.suhyun.findhouse.dto.*;
import org.suhyun.findhouse.entity.*;
import org.suhyun.findhouse.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;

    private final HouseImageRepository houseImageRepository;

    private final StructureRepository structureRepository;

    private final CostRepository costRepository;

    private final OptionRepository optionRepository;

    private final PriceRepository priceRepository;


    @Override
    public Long register(HouseDTO dto) {

        log.info("House DTO-------------------------------------------------------------------------------");

        log.info(dto);

        Map<String, Object> entityMap = dtoToEntity(dto);

        House house = (House) entityMap.get("house");

        List<HouseImage> houseImageList = (List<HouseImage>) entityMap.get("imgList");

        Cost cost = (Cost) entityMap.get("cost");

        Option option = (Option) entityMap.get("option");

        Price price = (Price) entityMap.get("price");

        Structure structure = (Structure) entityMap.get("structure");

        repository.save(house);
        optionRepository.save(option);
        priceRepository.save(price);
        structureRepository.save(structure);

        if (cost != null) {
            costRepository.save(cost);
        }

        if (houseImageList != null) {
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
                Arrays.asList((HouseImage) arr[1]),
                (Option) arr[2],
                (Price) arr[3],
                (Structure) arr[4],
                (Cost) arr[5],
                (Long) arr[6])
        );

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public PageResultDTO<HouseDTO, Object[]> getSearchList(PageRequestDTO requestDTO) {

        Page<Object[]> result = repository.searchPageWithAll(requestDTO);


        Function<Object[], HouseDTO> fn = (arr -> entityToDto(
                (House) arr[0],
                Arrays.asList((HouseImage) arr[1]),
                (Option) arr[2],
                (Price) arr[3],
                (Structure) arr[4],
                (Cost) arr[5],
                (Long) arr[6])
        );
        return new PageResultDTO<>(result, fn);
    }


    @Override
    public HouseDTO read(Long houseNum) {

        List<Object[]> result = repository.getHouseWithAll(houseNum);

        House house = (House) result.get(0)[0];

        List<HouseImage> houseImageList = new ArrayList<>();

        result.forEach(arr -> {
            if (arr[1] != null) {
                HouseImage houseImage = (HouseImage) arr[1];
                houseImageList.add(houseImage);
            }
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];
        Option option = (Option) result.get(0)[4];
        Price price = (Price) result.get(0)[5];
        Structure structure = (Structure) result.get(0)[6];
        Cost cost = (Cost) result.get(0)[7];


        return entityToDto(house, houseImageList, option, price, structure, cost, reviewCnt);
    }


    @Override
    public void modify(HouseDTO dto) {

        List<Object[]> result = repository.getHouseWithAll(dto.getHouseNum());

        if (!result.isEmpty()) {

            House house = (House) result.get(0)[0];
            Option option = (Option) result.get(0)[4];
            Price price = (Price) result.get(0)[5];
            Structure structure = (Structure) result.get(0)[6];
            Cost cost = (Cost) result.get(0)[7];
            List<HouseImage> houseImageList = new ArrayList<>();

            result.forEach(arr -> {
                if (arr[1] != null) {
                    HouseImage houseImage = (HouseImage) arr[1];
                    houseImageList.add(houseImage);
                }
            });

            CostDTO costDTO = dto.getCostDto();
            cost.changeCosts(costDTO.getTotalCost(), costDTO.isElectricity(), costDTO.isGas(), costDTO.isWater(),
                    costDTO.isCostTv(), costDTO.isInternet(), costDTO.isCostParking(), costDTO.isEtc(), costDTO.getCostContent());

            OptionDTO optionDTO = dto.getOptionDto();
            option.changeOptions(optionDTO.isTv(), optionDTO.isAirConditioner(), optionDTO.isRefrigerator()
                    , optionDTO.isWasher(), optionDTO.isDryer(), optionDTO.isInduction(), optionDTO.isGasStove(),
                    optionDTO.isSink(), optionDTO.isDesk(), optionDTO.isBookshelf(), optionDTO.isBed(),
                    optionDTO.isCloset(), optionDTO.isDishwasher(), optionDTO.isShoeRack());

            PriceDTO priceDTO = dto.getPriceDto();
            price.changePrices(priceDTO.getPrice(), priceDTO.getDeposit(), priceDTO.getMonthly());

            StructureDTO structureDTO = dto.getStructureDto();
            structure.chageStructures(structureDTO.getRoom(), structureDTO.getToilet(), structureDTO.getLivingRoom(),
                    structureDTO.getVeranda());

            house.changeStatus(dto.getStatus());
            house.changeTitle(dto.getTitle());
            house.changeContent(dto.getContent());
            house.changeInfo(dto.getAddress(),
                    dto.getContractType(),
                    dto.getMinTerm(),
                    dto.getBrokerage(),
                    dto.getMoveInDate(),
                    dto.getCompletionDate(),
                    dto.isLoan(),
                    dto.isElevator(),
                    dto.isPet(),
                    dto.isParking());

            checkModifyFile(dto);

            costRepository.save(cost);
            optionRepository.save(option);
            priceRepository.save(price);
            structureRepository.save(structure);
            repository.save(house);
        }
    }


    @Override
    @Transactional
    public void remove(Long houseNum) {

        houseImageRepository.deleteByHouse(houseNum);

        repository.deleteById(houseNum);

    }


    @Override
    @Transactional
    public void checkModifyFile(HouseDTO dto) {

        List<HouseImage> before = houseImageRepository.findByHouse(dto.getHouseNum());

        List<HouseImage> after = (List<HouseImage>) dtoToEntity(dto).get("imgList");

        before.stream().filter(hi -> {
            if (after.contains(hi)) {
                return true;
            } else {
                log.info("Delete existing images : " + hi);
                houseImageRepository.deleteById(hi.getImageNum());
                return false;
            }
        }).collect(Collectors.toList());

        after.forEach(hi->{
            if(before.contains(hi)){
                log.info("The Image File that existed : " + hi);
            }else{
                log.info("Upload a New Image File : " + hi);
                houseImageRepository.save(hi);
            }
        });
    }

    @Override
    public void updateView(Long houseNum) {
        repository.updateView(houseNum);
    }


    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QHouse qHouse = QHouse.house;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qHouse.houseNum.gt(0L);

        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t"))
            conditionBuilder.or(qHouse.title.contains(keyword));

        if (type.contains("c"))
            conditionBuilder.or(qHouse.content.contains(keyword));

        if (type.contains("w"))
            conditionBuilder.or(qHouse.id.contains(keyword));

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }



}
