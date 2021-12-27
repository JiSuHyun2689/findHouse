package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Structure;

public interface StructureRepository extends JpaRepository<Structure, Long> {

    @Modifying
    @Query("delete from Structure s where s.house =:house")
    void deletebyHouse(House house);

}