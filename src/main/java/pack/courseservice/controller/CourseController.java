package pack.courseservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pack.courseservice.dto.CourseCreateUpdateDTO;
import pack.courseservice.dto.CourseDTO;
import pack.courseservice.entity.CourseStatus;
import pack.courseservice.entity.Follow;
import pack.courseservice.repository.FollowRepository;
import pack.courseservice.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final FollowRepository followRepository;

    public CourseController(CourseService courseService, FollowRepository followRepository) {
        this.courseService = courseService;
        this.followRepository = followRepository;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseCreateUpdateDTO courseDTO) {
        log.info("Creating course with code: {}", courseDTO.getCourseCode());
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String courseId) {
        log.info("Fetching course with id: {}", courseId);
        CourseDTO course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/code/{courseCode}")
    public ResponseEntity<CourseDTO> getCourseByCourseCode(@PathVariable String courseCode) {
        log.info("Fetching course with code: {}", courseCode);
        CourseDTO course = courseService.getCourseByCourseCode(courseCode);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/module/{moduleCode}")
    public ResponseEntity<CourseDTO> getCourseByModuleCode(@PathVariable String moduleCode) {
        log.info("Fetching course with module code: {}", moduleCode);
        CourseDTO course = courseService.getCourseByModuleCode(moduleCode);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("Fetching all courses");
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CourseDTO>> getCoursesByStatus(@PathVariable CourseStatus status) {
        log.info("Fetching courses with status: {}", status);
        List<CourseDTO> courses = courseService.getCoursesByStatus(status);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCoursesByTitle(@RequestParam String title) {
        log.info("Searching courses with title: {}", title);
        List<CourseDTO> courses = courseService.searchCoursesByTitle(title);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable String courseId,
            @RequestBody CourseCreateUpdateDTO courseDTO) {
        log.info("Updating course with id: {}", courseId);
        CourseDTO updatedCourse = courseService.updateCourse(courseId, courseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        log.info("Deleting course with id: {}", courseId);
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/follow")
    public Follow follow(@PathVariable String id) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT")))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        Follow f = new Follow();
        f.setCourseId(id);
        f.setStudentId(Long.valueOf((String) auth.getPrincipal()));
        return followRepository.save(f);
    }
}
