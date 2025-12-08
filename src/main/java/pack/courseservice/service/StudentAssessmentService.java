package pack.courseservice.service;

import pack.courseservice.dto.StudentAssessmentCreateUpdateDTO;
import pack.courseservice.dto.StudentAssessmentDTO;
import pack.courseservice.entity.SubmissionStatus;

import java.util.List;

public interface StudentAssessmentService {

    StudentAssessmentDTO createStudentAssessment(StudentAssessmentCreateUpdateDTO assessmentDTO);

    StudentAssessmentDTO getStudentAssessmentById(Long id);

    List<StudentAssessmentDTO> getStudentAssessmentsByStudentId(Long studentId);

    List<StudentAssessmentDTO> getStudentAssessmentsByAssessmentId(Long assessmentId);

    List<StudentAssessmentDTO> getStudentAssessmentsByStudentIdAndStatus(Long studentId, SubmissionStatus status);

    List<StudentAssessmentDTO> getStudentAssessmentsByAssessmentIdAndStatus(Long assessmentId, SubmissionStatus status);

    List<StudentAssessmentDTO> getAllStudentAssessments();

    StudentAssessmentDTO updateStudentAssessment(Long id, StudentAssessmentCreateUpdateDTO assessmentDTO);

    void deleteStudentAssessment(Long id);
}

