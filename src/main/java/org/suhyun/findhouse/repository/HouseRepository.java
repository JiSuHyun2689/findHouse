package org.suhyun.findhouse.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.suhyun.findhouse.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>, QuerydslPredicateExecutor<House> {

    @Query("select h, hi, avg(coalesce(r.grade, 0)), count(distinct r) " +
            "from House h " +
            "left outer join Review r on r.member = h.id " +
            "left outer join HouseImage hi on hi.house = h " +
            "group by h")
    Page<Object[]> getListPage(Pageable pageable);

}
