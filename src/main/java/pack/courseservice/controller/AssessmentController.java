package pack.courseservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.courseservice.dto.AssessmentCreateUpdateDTO;
import pack.courseservice.dto.AssessmentDTO;
import pack.courseservice.entity.AssessmentStatus;
import pack.courseservice.service.AssessmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assessments")
public class AssessmentController {

    private static final Logger log = LoggerFactory.getLogger(AssessmentController.class);
    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping
    public ResponseEntity<AssessmentDTO> createAssessment(@RequestBody AssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Creating assessment: {}", assessmentDTO.getTitle());
        AssessmentDTO createdAssessment = assessmentService.createAssessment(assessmentDTO);
        return new ResponseEntity<>(createdAssessment, HttpStatus.CREATED);
    }

    @GetMapping("/{assessmentId}")
    public ResponseEntity<AssessmentDTO> getAssessmentById(@PathVariable Long assessmentId) {
        log.info("Fetching assessment with id: {}", assessmentId);
        AssessmentDTO assessment = assessmentService.getAssessmentById(assessmentId);
        return ResponseEntity.ok(assessment);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AssessmentDTO>> getAssessmentsByCourseId(@PathVariable String courseId) {
        log.info("Fetching assessments for course: {}", courseId);
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByCourseId(courseId);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/course/{courseId}/status/{status}")
    public ResponseEntity<List<AssessmentDTO>> getAssessmentsByCourseIdAndStatus(
            @PathVariable String courseId,
            @PathVariable AssessmentStatus status) {
        log.info("Fetching assessments for course: {} with status: {}", courseId, status);
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByCourseIdAndStatus(courseId, status);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/course/{courseId}/week/{weekNumber}")
    public ResponseEntity<List<AssessmentDTO>> getAssessmentsByCourseIdAndWeekNumber(
            @PathVariable String courseId,
            @PathVariable Integer weekNumber) {
        log.info("Fetching assessments for course: {} with week number: {}", courseId, weekNumber);
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByCourseIdAndWeekNumber(courseId, weekNumber);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping
    public ResponseEntity<List<AssessmentDTO>> getAllAssessments() {
        log.info("Fetching all assessments");
        List<AssessmentDTO> assessments = assessmentService.getAllAssessments();
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AssessmentDTO>> getAssessmentsByStatus(@PathVariable AssessmentStatus status) {
        log.info("Fetching assessments with status: {}", status);
        List<AssessmentDTO> assessments = assessmentService.getAssessmentsByStatus(status);
        return ResponseEntity.ok(assessments);
    }

    @PutMapping("/{assessmentId}")
    public ResponseEntity<AssessmentDTO> updateAssessment(
            @PathVariable Long assessmentId,
            @RequestBody AssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Updating assessment with id: {}", assessmentId);
        AssessmentDTO updatedAssessment = assessmentService.updateAssessment(assessmentId, assessmentDTO);
        return ResponseEntity.ok(updatedAssessment);
    }

    @DeleteMapping("/{assessmentId}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long assessmentId) {
        log.info("Deleting assessment with id: {}", assessmentId);
        assessmentService.deleteAssessment(assessmentId);
        return ResponseEntity.noContent().build();
    }
}
