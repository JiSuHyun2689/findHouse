package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Option;

import javax.transaction.Transactional;
import java.util.Optional;

public interface OptionRepository extends JpaRepository <Option, Long> {

    @Modifying
    @Transactional
    @Query("delete from Option o where o.house.houseNum =:houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);

    @Query("select o from Option o where o.house.houseNum =:houseNum")
    Optional<Option> findByHouse(@Param("houseNum")Long houseNum);
}
