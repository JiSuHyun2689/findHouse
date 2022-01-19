package org.suhyun.findhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhyun.findhouse.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
