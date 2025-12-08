package pack.courseservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pack.courseservice.dto.CourseCreateUpdateDTO;
import pack.courseservice.dto.CourseDTO;
import pack.courseservice.entity.Course;
import pack.courseservice.entity.CourseStatus;
import pack.courseservice.exception.ResourceNotFoundException;
import pack.courseservice.mapper.CourseMapper;
import pack.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDTO createCourse(CourseCreateUpdateDTO courseDTO) {
        log.info("Creating new course with code: {}", courseDTO.getCourseCode());

        Course course = courseMapper.toEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);

        log.info("Course created successfully with id: {}", savedCourse.getId());
        return courseMapper.toDTO(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(String courseId) {
        log.info("Fetching course with id: {}", courseId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return courseMapper.toDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseByCourseCode(String courseCode) {
        log.info("Fetching course with code: {}", courseCode);
        Course course = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with code: " + courseCode));
        return courseMapper.toDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseByModuleCode(String moduleCode) {
        log.info("Fetching course with module code: {}", moduleCode);
        Course course = courseRepository.findByModuleCode(moduleCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with module code: " + moduleCode));
        return courseMapper.toDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getCoursesByStatus(CourseStatus status) {
        log.info("Fetching courses with status: {}", status);
        return courseRepository.findByStatus(status)
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> searchCoursesByTitle(String title) {
        log.info("Searching courses with title containing: {}", title);
        return courseRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO updateCourse(String courseId, CourseCreateUpdateDTO courseDTO) {
        log.info("Updating course with id: {}", courseId);

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        courseMapper.updateEntityFromDTO(courseDTO, course);
        Course updatedCourse = courseRepository.save(course);

        log.info("Course updated successfully with id: {}", updatedCourse.getId());
        return courseMapper.toDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(String courseId) {
        log.info("Deleting course with id: {}", courseId);

        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }

        courseRepository.deleteById(courseId);
        log.info("Course deleted successfully with id: {}", courseId);
    }
}
