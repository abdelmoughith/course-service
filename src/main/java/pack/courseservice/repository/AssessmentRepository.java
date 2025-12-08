package pack.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.courseservice.entity.Assessment;
import pack.courseservice.entity.AssessmentStatus;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByCourseId(String courseId);

    List<Assessment> findByCourseIdAndStatus(String courseId, AssessmentStatus status);

    List<Assessment> findByCourseIdAndWeekNumber(String courseId, Integer weekNumber);

    List<Assessment> findByStatus(AssessmentStatus status);
}

