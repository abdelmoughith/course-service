package pack.courseservice.mapper;

import org.springframework.stereotype.Component;
import pack.courseservice.dto.AssessmentCreateUpdateDTO;
import pack.courseservice.dto.AssessmentDTO;
import pack.courseservice.entity.Assessment;

@Component
public class AssessmentMapper {

    public AssessmentDTO toDTO(Assessment assessment) {
        if (assessment == null) {
            return null;
        }

        AssessmentDTO dto = new AssessmentDTO();
        dto.setId(assessment.getId());
        dto.setTitle(assessment.getTitle());
        dto.setDescription(assessment.getDescription());
        dto.setAssessmentType(assessment.getAssessmentType());
        dto.setMaxMarks(assessment.getMaxMarks());
        dto.setDurationMinutes(assessment.getDurationMinutes());
        dto.setStatus(assessment.getStatus());
        dto.setWeekNumber(assessment.getWeekNumber());
        dto.setCreatedAt(assessment.getCreatedAt());
        dto.setUpdatedAt(assessment.getUpdatedAt());
        dto.setCourseId(assessment.getCourse().getId());

        return dto;
    }

    public Assessment toEntity(AssessmentCreateUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        Assessment assessment = new Assessment();
        updateEntityFromDTO(dto, assessment);

        return assessment;
    }

    public void updateEntityFromDTO(AssessmentCreateUpdateDTO dto, Assessment assessment) {
        if (dto == null) {
            return;
        }

        if (dto.getTitle() != null) {
            assessment.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            assessment.setDescription(dto.getDescription());
        }
        if (dto.getAssessmentType() != null) {
            assessment.setAssessmentType(dto.getAssessmentType());
        }
        if (dto.getMaxMarks() != null) {
            assessment.setMaxMarks(dto.getMaxMarks());
        }
        if (dto.getDurationMinutes() != null) {
            assessment.setDurationMinutes(dto.getDurationMinutes());
        }
        if (dto.getStatus() != null) {
            assessment.setStatus(dto.getStatus());
        }
        if (dto.getWeekNumber() != null) {
            assessment.setWeekNumber(dto.getWeekNumber());
        }
    }
}

