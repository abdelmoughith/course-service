package pack.courseservice.service;

import pack.courseservice.dto.AssessmentCreateUpdateDTO;
import pack.courseservice.dto.AssessmentDTO;
import pack.courseservice.entity.AssessmentStatus;

import java.util.List;

public interface AssessmentService {

    AssessmentDTO createAssessment(AssessmentCreateUpdateDTO assessmentDTO);

    AssessmentDTO getAssessmentById(Long assessmentId);

    List<AssessmentDTO> getAssessmentsByCourseId(String courseId);

    List<AssessmentDTO> getAssessmentsByCourseIdAndStatus(String courseId, AssessmentStatus status);

    List<AssessmentDTO> getAssessmentsByCourseIdAndWeekNumber(String courseId, Integer weekNumber);

    List<AssessmentDTO> getAllAssessments();

    List<AssessmentDTO> getAssessmentsByStatus(AssessmentStatus status);

    AssessmentDTO updateAssessment(Long assessmentId, AssessmentCreateUpdateDTO assessmentDTO);

    void deleteAssessment(Long assessmentId);
}

