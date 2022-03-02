package org.suhyun.findhouse.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.entity.House;


public interface SearchHouseRepository  {

    House search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

    Page<Object[]> searchPageWithAll(PageRequestDTO requestDTO);
}
