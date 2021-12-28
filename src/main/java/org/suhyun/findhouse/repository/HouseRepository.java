package org.suhyun.findhouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.House;

public interface HouseRepository extends JpaRepository<House, Long> {

}
