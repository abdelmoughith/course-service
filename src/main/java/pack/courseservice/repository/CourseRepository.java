package pack.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.courseservice.entity.Course;
import pack.courseservice.entity.CourseStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    Optional<Course> findByCourseCode(String courseCode);

    Optional<Course> findByModuleCode(String moduleCode);

    List<Course> findByStatus(CourseStatus status);

    List<Course> findByTitleContainingIgnoreCase(String title);
}

