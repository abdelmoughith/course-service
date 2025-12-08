package pack.courseservice.mapper;

import org.springframework.stereotype.Component;
import pack.courseservice.dto.CourseCreateUpdateDTO;
import pack.courseservice.dto.CourseDTO;
import pack.courseservice.entity.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCourseCode(course.getCourseCode());
        dto.setModuleCode(course.getModuleCode());
        dto.setPresentationLength(course.getPresentationLength());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setStatus(course.getStatus());
        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());

        return dto;
    }

    public Course toEntity(CourseCreateUpdateDTO dto) {
        if (dto == null) {
            return null;
        }

        Course course = new Course();
        updateEntityFromDTO(dto, course);

        return course;
    }

    public void updateEntityFromDTO(CourseCreateUpdateDTO dto, Course course) {
        if (dto == null) {
            return;
        }

        if (dto.getCourseCode() != null) {
            course.setCourseCode(dto.getCourseCode());
        }
        if (dto.getModuleCode() != null) {
            course.setModuleCode(dto.getModuleCode());
        }
        if (dto.getPresentationLength() != null) {
            course.setPresentationLength(dto.getPresentationLength());
        }
        if (dto.getTitle() != null) {
            course.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            course.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            course.setStatus(dto.getStatus());
        }
    }
}

