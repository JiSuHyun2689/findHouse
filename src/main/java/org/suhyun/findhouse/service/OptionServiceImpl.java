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
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.repository.OptionRepository;

import java.util.Optional;
import java.util.function.Function;


@Service
@Log4j2
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService{

    private final OptionRepository optionRepository;

    @Override
    public Long register(OptionDTO dto) {

        log.info("Option DTO register------------------------------------------------------------");
        log.info(dto);

        Option entity = dtoToEntity(dto);
        log.info(entity);

        optionRepository.save(entity);
        return entity.getOption_num();
    }

    @Override
    public PageResultDTO<OptionDTO, Option> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        Page<Option> result = optionRepository.findAll(pageable);

        Function<Option, OptionDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public OptionDTO read(Long houseNum) {

        Optional<Option> result = optionRepository.findByHouse(houseNum);

        return result.isPresent() ? entityToDto(result.get()) : null;

    }

    @Override
    public void modify(OptionDTO dto) {

        Optional<Option> result = optionRepository.findByHouse(dto.getHouseNum());

        if(result.isPresent()){

            Option entity = result.get();

            entity.changeOptions(dto.isTv(), dto.isAirConditioner(), dto.isRefrigerator(), dto.isWasher(), dto.isDryer()
            ,dto.isInduction(), dto.isGasStove(), dto.isSink(), dto.isDesk(), dto.isBookshelf(), dto.isBed(), dto.isCloset(), dto.isDishwasher(), dto.isShoeRack());

            optionRepository.save(entity);
        }

    }

    @Override
    public void remove(Long houseNum) {

        optionRepository.deleteByHouse(houseNum);

    }
}
