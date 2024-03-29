package org.suhyun.findhouse.service;

import org.suhyun.findhouse.dto.*;
import org.suhyun.findhouse.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface HouseService {

    Long register(HouseDTO dto);

    public PageResultDTO<HouseDTO, Object[]> getList(PageRequestDTO requestDTO);

    public PageResultDTO<HouseDTO, Object[]> getSearchList(PageRequestDTO requestDTO);

    HouseDTO read(Long houseNum);

    void modify(HouseDTO dto);

    void remove(Long houseNum);

    void checkModifyFile(HouseDTO dto);

    void updateView(Long houseNum);

    default Map<String, Object> dtoToEntity(HouseDTO dto) {

        Map<String, Object> entityMap = new HashMap<>();

        House entity = House.builder().houseNum(dto.getHouseNum()).address(dto.getAddress()).area(dto.getArea()).brokerage(dto.getBrokerage())
                .buildingType(dto.getBuildingType()).contractType(dto.getContractType()).completionDate(dto.getCompletionDate())
                .content(dto.getContent()).elevator(dto.isElevator()).loan(dto.isLoan()).minTerm(dto.getMinTerm()).id(dto.getId())
                .moveInDate(dto.getMoveInDate()).parking(dto.isParking()).pet(dto.isPet()).status(dto.getStatus()).theFloor(dto.getTheFloor())
                .wholeFloor(dto.getWholeFloor()).view(dto.getView()).houseNum(dto.getHouseNum()).title(dto.getTitle()).build();

        List<HouseImageDTO> imageDTOList = dto.getImageDTOList();
        OptionDTO optionDTO = dto.getOptionDto();
        PriceDTO priceDTO = dto.getPriceDto();
        StructureDTO structureDTO = dto.getStructureDto();
        CostDTO costDTO = dto.getCostDto();


        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<HouseImage> houseImageList = imageDTOList.stream().map(houseImageDTO -> {
                HouseImage houseImage = HouseImage.builder()
                        .imageNum(houseImageDTO.getImageNum())
                        .path(houseImageDTO.getPath())
                        .imageName(houseImageDTO.getImageName())
                        .uuid(houseImageDTO.getUuid())
                        .house(entity)
                        .build();
                return houseImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", houseImageList);
        }

        Option option = Option.builder().optionNum(optionDTO.getOptionNum())
                .airConditioner(optionDTO.isAirConditioner()).bed(optionDTO.isBed()).bookshelf(optionDTO.isBookshelf())
                .closet(optionDTO.isCloset()).desk(optionDTO.isDesk()).dishwasher(optionDTO.isDishwasher())
                .dryer(optionDTO.isDryer()).gasStove(optionDTO.isGasStove()).induction(optionDTO.isInduction())
                .refrigerator(optionDTO.isRefrigerator()).sink(optionDTO.isSink()).tv(optionDTO.isTv())
                .washer(optionDTO.isWasher()).house(entity).build();

        Price price = Price.builder().price(priceDTO.getPrice()).house(entity).priceNum(priceDTO.getPriceNum())
                .monthly(priceDTO.getMonthly()).deposit(priceDTO.getDeposit()).build();

        Structure structure = Structure.builder().livingRoom(structureDTO.getLivingRoom()).room(structureDTO.getRoom())
                .toilet(structureDTO.getToilet()).veranda(structureDTO.getVeranda())
                .structureNum(structureDTO.getStructureNum()).house(entity).build();

        Cost cost = Cost.builder().costNum(costDTO.getCostNum()).content(costDTO.getCostContent()).totalCost(costDTO.getTotalCost())
                .electricity(costDTO.isElectricity()).etc(costDTO.isEtc()).gas(costDTO.isGas()).internet(costDTO.isInternet())
                .parking(costDTO.isCostParking()).tv(costDTO.isCostTv()).water(costDTO.isWater())
                .house(entity).build();

        entityMap.put("house", entity);
        entityMap.put("option", option);
        entityMap.put("price", price);
        entityMap.put("structure", structure);
        entityMap.put("cost", cost);

        return entityMap;
    }

    default HouseDTO entityToDto(House entity, List<HouseImage> houseImages, Option option, Price price, Structure structure, Cost cost, Long reviewCnt) {

        HouseDTO dto = HouseDTO.builder().houseNum(entity.getHouseNum()).buildingType(entity.getBuildingType())
                .contractType(entity.getContractType()).address(entity.getAddress()).area(entity.getArea())
                .brokerage(entity.getBrokerage()).completionDate(entity.getCompletionDate()).content(entity.getContent())
                .elevator(entity.isElevator()).id(entity.getId()).loan(entity.isLoan()).minTerm(entity.getMinTerm())
                .pet(entity.isPet()).moveInDate(entity.getMoveInDate()).modDate(entity.getModDate()).parking(entity.isParking())
                .regDate(entity.getRegDate()).status(entity.getStatus()).theFloor(entity.getTheFloor())
                .wholeFloor(entity.getWholeFloor()).view(entity.getView()).title(entity.getTitle())
                .build();

        List<HouseImageDTO> houseImageDTOList = houseImages.stream().map(houseImage -> HouseImageDTO.builder()
                .imageNum(houseImage.getImageNum())
                .imageName(houseImage.getImageName())
                .path(houseImage.getPath())
                .uuid(houseImage.getUuid())
                .build()).collect(Collectors.toList());

        OptionDTO opdionDto = OptionDTO.builder().optionNum(option.getOptionNum()).bed(option.isBed()).airConditioner(option.isAirConditioner())
                .bookshelf(option.isBookshelf()).desk(option.isDesk()).closet(option.isCloset()).dishwasher(option.isDishwasher())
                .dryer(option.isDryer()).gasStove(option.isGasStove()).induction(option.isInduction()).refrigerator(option.isRefrigerator())
                .sink(option.isSink()).shoeRack(option.isShoeRack()).tv(option.isTv()).washer(option.isWasher()).build();

        PriceDTO priceDto = PriceDTO.builder().price(price.getPrice()).priceNum(price.getPriceNum())
                .monthly(price.getMonthly()).deposit(price.getDeposit()).build();

        StructureDTO structureDto = StructureDTO.builder().structureNum(structure.getStructureNum()).livingRoom(structure.getLivingRoom())
                .room(structure.getRoom()).toilet(structure.getToilet()).veranda(structure.getVeranda()).build();

        CostDTO costDto = CostDTO.builder().costNum(cost.getCostNum()).costContent(cost.getContent()).totalCost(cost.getTotalCost()).etc(cost.isEtc())
                .electricity(cost.isElectricity()).gas(cost.isGas()).internet(cost.isInternet())
                .costTv(cost.isTv()).costParking(entity.isParking()).water(cost.isWater()).build();

        dto.setOptionDto(opdionDto);
        dto.setPriceDto(priceDto);
        dto.setStructureDto(structureDto);
        dto.setCostDto(costDto);
        dto.setImageDTOList(houseImageDTOList);
        //dto.setAvg(avg);
        dto.setReviewCnt(reviewCnt.intValue());

        return dto;
    }


}
