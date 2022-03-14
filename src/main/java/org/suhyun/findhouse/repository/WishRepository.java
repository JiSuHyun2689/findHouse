package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Member;
import org.suhyun.findhouse.entity.Review;
import org.suhyun.findhouse.entity.Wish;

import javax.transaction.Transactional;
import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Wish> findByHouse(House house);

    @Modifying
    @Query("delete from Wish w " +
            "where w.member.id = :id")
    void deleteByMember(@Param("id")String id);

    @Modifying
    @Transactional
    @Query("delete from Wish w " +
            "where w.house.houseNum = :houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);

}
