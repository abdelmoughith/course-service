package pack.courseservice.service;

import pack.courseservice.dto.CourseCreateUpdateDTO;
import pack.courseservice.dto.CourseDTO;
import pack.courseservice.entity.CourseStatus;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseCreateUpdateDTO courseDTO);

    CourseDTO getCourseById(String courseId);

    CourseDTO getCourseByCourseCode(String courseCode);

    CourseDTO getCourseByModuleCode(String moduleCode);

    List<CourseDTO> getAllCourses();

    List<CourseDTO> getCoursesByStatus(CourseStatus status);

    List<CourseDTO> searchCoursesByTitle(String title);

    CourseDTO updateCourse(String courseId, CourseCreateUpdateDTO courseDTO);

    void deleteCourse(String courseId);
}

