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
import org.suhyun.findhouse.dto.PriceDTO;
import org.suhyun.findhouse.entity.Option;
import org.suhyun.findhouse.entity.Price;
import org.suhyun.findhouse.repository.PriceRepository;

import java.util.Optional;
import java.util.function.Function;


@Service
@Log4j2
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService{

    private final PriceRepository repository;


    @Override
    public Long register(PriceDTO dto) {

        log.info("Option DTO register------------------------------------------------------------");
        log.info(dto);

        Price entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getPriceNum();
    }


    @Override
    public PageResultDTO<PriceDTO, Price> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("houseNum").descending());

        Page<Price> result = repository.findAll(pageable);

        Function<Price, PriceDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }


    @Override
    public PriceDTO read(Long houseNum) {

        Optional<Price> result = repository.findByHouse(houseNum);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }


    @Override
    public void modify(PriceDTO dto) {

        Optional<Price> result = repository.findByHouse(dto.getHouseNum());

        if(result.isPresent()){

            Price entity = result.get();

            entity.changePrices(dto.getPrice(), dto.getDeposit(), dto.getMonthly());

            repository.save(entity);
        }
    }


    @Override
    public void remove(Long houseNum) {
        repository.deleteByHouse(houseNum);
    }
}
