package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhyun.findhouse.entity.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}
