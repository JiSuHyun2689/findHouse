package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.House;

public interface CostRepository extends JpaRepository<Cost, Long> {

    @Modifying
    @Query("delete from Cost c where c.house =:house")
    void deletebyHouse(House house);
}
