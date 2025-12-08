package pack.courseservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.courseservice.dto.StudentAssessmentCreateUpdateDTO;
import pack.courseservice.dto.StudentAssessmentDTO;
import pack.courseservice.entity.SubmissionStatus;
import pack.courseservice.service.StudentAssessmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student-assessments")
public class StudentAssessmentController {

    private static final Logger log = LoggerFactory.getLogger(StudentAssessmentController.class);
    private final StudentAssessmentService studentAssessmentService;

    public StudentAssessmentController(StudentAssessmentService studentAssessmentService) {
        this.studentAssessmentService = studentAssessmentService;
    }

    @PostMapping
    public ResponseEntity<StudentAssessmentDTO> createStudentAssessment(@RequestBody StudentAssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Creating student assessment for student: {}", assessmentDTO.getStudentId());
        StudentAssessmentDTO createdAssessment = studentAssessmentService.createStudentAssessment(assessmentDTO);
        return new ResponseEntity<>(createdAssessment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentAssessmentDTO> getStudentAssessmentById(@PathVariable Long id) {
        log.info("Fetching student assessment with id: {}", id);
        StudentAssessmentDTO assessment = studentAssessmentService.getStudentAssessmentById(id);
        return ResponseEntity.ok(assessment);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentAssessmentDTO>> getStudentAssessmentsByStudentId(@PathVariable Long studentId) {
        log.info("Fetching student assessments for student: {}", studentId);
        List<StudentAssessmentDTO> assessments = studentAssessmentService.getStudentAssessmentsByStudentId(studentId);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<StudentAssessmentDTO>> getStudentAssessmentsByAssessmentId(@PathVariable Long assessmentId) {
        log.info("Fetching student assessments for assessment: {}", assessmentId);
        List<StudentAssessmentDTO> assessments = studentAssessmentService.getStudentAssessmentsByAssessmentId(assessmentId);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/student/{studentId}/status/{status}")
    public ResponseEntity<List<StudentAssessmentDTO>> getStudentAssessmentsByStudentIdAndStatus(
            @PathVariable Long studentId,
            @PathVariable SubmissionStatus status) {
        log.info("Fetching student assessments for student: {} with status: {}", studentId, status);
        List<StudentAssessmentDTO> assessments = studentAssessmentService.getStudentAssessmentsByStudentIdAndStatus(studentId, status);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/assessment/{assessmentId}/status/{status}")
    public ResponseEntity<List<StudentAssessmentDTO>> getStudentAssessmentsByAssessmentIdAndStatus(
            @PathVariable Long assessmentId,
            @PathVariable SubmissionStatus status) {
        log.info("Fetching student assessments for assessment: {} with status: {}", assessmentId, status);
        List<StudentAssessmentDTO> assessments = studentAssessmentService.getStudentAssessmentsByAssessmentIdAndStatus(assessmentId, status);
        return ResponseEntity.ok(assessments);
    }

    @GetMapping
    public ResponseEntity<List<StudentAssessmentDTO>> getAllStudentAssessments() {
        log.info("Fetching all student assessments");
        List<StudentAssessmentDTO> assessments = studentAssessmentService.getAllStudentAssessments();
        return ResponseEntity.ok(assessments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentAssessmentDTO> updateStudentAssessment(
            @PathVariable Long id,
            @RequestBody StudentAssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Updating student assessment with id: {}", id);
        StudentAssessmentDTO updatedAssessment = studentAssessmentService.updateStudentAssessment(id, assessmentDTO);
        return ResponseEntity.ok(updatedAssessment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentAssessment(@PathVariable Long id) {
        log.info("Deleting student assessment with id: {}", id);
        studentAssessmentService.deleteStudentAssessment(id);
        return ResponseEntity.noContent().build();
    }
}
