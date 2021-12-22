package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhyun.findhouse.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
