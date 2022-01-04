package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.Cost;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Structure;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CostRepository extends JpaRepository<Cost, Long> {

    @Modifying
    @Transactional
    @Query("delete from Cost c where c.house.houseNum =:houseNum")
    void deleteByHouse(Long houseNum);

    @Query("select c from Cost c where c.house.houseNum =:houseNum")
    Optional<Cost> findByHouse(Long houseNum);
}
