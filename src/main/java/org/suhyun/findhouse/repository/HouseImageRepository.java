package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.HouseImage;

public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {

    @Modifying
    @Query("delete from HouseImage h " +
            "where h.houseNum  = :houseNum")
    void deletebyHouse(HouseImage houseImage);
}
