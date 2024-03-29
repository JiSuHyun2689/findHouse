package org.suhyun.findhouse.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.repository.search.SearchHouseRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long>, QuerydslPredicateExecutor<House>, SearchHouseRepository {

/*
    @Query("select h, hi, avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from House h " +
            "left outer join Review r on r.member = h.id " +
            "left outer join HouseImage hi on hi.house = h " +
            "group by h")
    Page<Object[]> getListPage(Pageable pageable);

 */

    @Modifying
    @Transactional
    @Query("update House h " +
            "set h.view = h.view + 1 " +
            "where h.houseNum =:houseNum ")
    int updateView(@Param("houseNum") Long houseNum);


    @Query("select h, hi, avg(coalesce(r.grade, 0)), count(distinct r), o, p, s, c " +
            "from House h " +
            "inner join Option o on o.house = h " +
            "inner join Price p on p.house = h " +
            "inner join Structure s on s.house = h " +
            "inner join Cost c on c.house = h " +
            "left outer join Review r on r.member = h.id " +
            "left outer join HouseImage hi on hi.house = h " +
            "group by h")
    Page<Object[]> getListPage(Pageable pageable);


    @Query("select h, hi, avg(coalesce(r.grade, 0)), count(r), o, p, s, c " +
            "from House h " +
            "inner join Option o on o.house = h " +
            "inner join Price p on p.house = h " +
            "inner join Structure s on s.house = h " +
            "inner join Cost c on c.house = h " +
            "left outer join HouseImage hi on hi.house = h " +
            "left outer join Review r on r.targetId = h.id " +
            "where h.houseNum = :houseNum " +
            "group by hi")
    List<Object[]> getHouseWithAll(@Param("houseNum") Long houseNum);



    @Modifying
    @Transactional
    @Query("delete from HouseImage hi where hi.house.houseNum =:houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);


    /*
    @Query(value = "delete h, o, p, s, c, hi, r " +
            "from House h " +
            "inner join Option o on o.house = h " +
            "inner join Price p on p.house = h " +
            "inner join Structure s on s.house = h " +
            "inner join Cost c on c.house = h " +
            "left outer join HouseImage hi on hi.house = h " +
            "left outer join Review r on r.member = h.id " +
            "where h.houseNum = :houseNum ")
    public void deleteByHouseNum(@Param("houseNum") Long houseNum);

     */
}
