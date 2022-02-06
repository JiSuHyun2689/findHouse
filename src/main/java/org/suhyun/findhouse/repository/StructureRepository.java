package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.suhyun.findhouse.entity.House;
import org.suhyun.findhouse.entity.Structure;

import javax.transaction.Transactional;
import java.util.Optional;

public interface StructureRepository extends JpaRepository<Structure, Long> {

    @Modifying
    @Transactional
    @Query("delete from Structure s where s.house.houseNum =:houseNum")
    void deleteByHouse(@Param("houseNum")Long houseNum);

    @Query("select s from Structure s where s.house.houseNum =:houseNum")
    Optional<Structure> findByHouse(@Param("houseNum")Long houseNum);
}
