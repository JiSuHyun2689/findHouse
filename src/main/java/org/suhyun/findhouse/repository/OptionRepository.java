package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Option;

public interface OptionRepository extends JpaRepository <Option, Long> {

    @Modifying
    @Query("delete from Option o where o.house =:house")
    void deletebyHouse(House house);
}
