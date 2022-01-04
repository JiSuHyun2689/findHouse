package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Price;
import org.suhyun.findhouse.entity.Structure;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Modifying
    @Transactional
    @Query("delete from Price p where p.house.houseNum =:houseNum")
    void deleteByHouse(Long houseNum);

    @Query("select p from Price p where p.house.houseNum =:houseNum")
    Optional<Price> findByHouse(Long houseNum);
}
