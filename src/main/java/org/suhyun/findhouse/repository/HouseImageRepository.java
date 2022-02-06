package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.HouseImage;

public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {

    @Modifying
    @Query("delete from HouseImage hI where hI.house.houseNum =:houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);
}
