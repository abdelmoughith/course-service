package pack.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.courseservice.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}

