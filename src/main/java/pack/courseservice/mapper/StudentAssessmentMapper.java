package pack.courseservice.mapper;

import org.springframework.stereotype.Component;
import pack.courseservice.dto.StudentAssessmentCreateUpdateDTO;
import pack.courseservice.dto.StudentAssessmentDTO;
import pack.courseservice.entity.StudentAssessment;

@Component
public class StudentAssessmentMapper {

    public StudentAssessmentDTO toDTO(StudentAssessment studentAssessment) {
        if (studentAssessment == null) {
            return null;
        }

        StudentAssessmentDTO dto = new StudentAssessmentDTO();
        dto.setId(studentAssessment.getId());
        dto.setStudentId(studentAssessment.getStudentId());
        dto.setMarksObtained(studentAssessment.getMarksObtained());
        dto.setSubmissionStatus(studentAssessment.getSubmissionStatus());
        dto.setSubmittedAt(studentAssessment.getSubmittedAt());
        dto.setGradedAt(studentAssessment.getGradedAt());
        dto.setFeedback(studentAssessment.getFeedback());
        dto.setCreatedAt(studentAssessment.getCreatedAt());
        dto.setUpdatedAt(studentAssessment.getUpdatedAt());
        dto.setAssessmentId(studentAssessment.getAssessment().getId());

        return dto;
    }

    public StudentAssessment toEntity(StudentAssessmentCreateUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        StudentAssessment studentAssessment = new StudentAssessment();
        updateEntityFromDTO(dto, studentAssessment);

        return studentAssessment;
    }

    public void updateEntityFromDTO(StudentAssessmentCreateUpdateDTO dto, StudentAssessment studentAssessment) {
        if (dto == null) {
            return;
        }

        if (dto.getStudentId() != null) {
            studentAssessment.setStudentId(dto.getStudentId());
        }
        if (dto.getMarksObtained() != null) {
            studentAssessment.setMarksObtained(dto.getMarksObtained());
        }
        if (dto.getSubmissionStatus() != null) {
            studentAssessment.setSubmissionStatus(dto.getSubmissionStatus());
        }
        if (dto.getSubmittedAt() != null) {
            studentAssessment.setSubmittedAt(dto.getSubmittedAt());
        }
        if (dto.getGradedAt() != null) {
            studentAssessment.setGradedAt(dto.getGradedAt());
        }
        if (dto.getFeedback() != null) {
            studentAssessment.setFeedback(dto.getFeedback());
        }
    }
}

