package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.HouseImage;
import org.suhyun.findhouse.entity.Review;
import org.suhyun.findhouse.entity.Structure;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {

    @Modifying
    @Query("delete from HouseImage hI where hI.house.houseNum =:houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);

    @Query("select hi from HouseImage hi where hi.house.houseNum =:houseNum")
    List<HouseImage> findByHouse(@Param("houseNum")Long houseNum);
}
