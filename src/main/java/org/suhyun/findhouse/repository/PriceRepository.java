package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Modifying
    @Query("delete from Price p where p.house =:house")
    void deletebyHouse(House house);
}
