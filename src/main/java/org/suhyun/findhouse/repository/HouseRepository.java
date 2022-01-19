package org.suhyun.findhouse.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.suhyun.findhouse.entity.House;

public interface HouseRepository extends JpaRepository<House, Long>, QuerydslPredicateExecutor<House> {

}
