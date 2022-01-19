package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhyun.findhouse.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
