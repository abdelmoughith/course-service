package pack.courseservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pack.courseservice.dto.AssessmentCreateUpdateDTO;
import pack.courseservice.dto.AssessmentDTO;
import pack.courseservice.entity.Assessment;
import pack.courseservice.entity.AssessmentStatus;
import pack.courseservice.entity.Course;
import pack.courseservice.exception.ResourceNotFoundException;
import pack.courseservice.mapper.AssessmentMapper;
import pack.courseservice.repository.AssessmentRepository;
import pack.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

    private static final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);
    private final AssessmentRepository assessmentRepository;
    private final CourseRepository courseRepository;
    private final AssessmentMapper assessmentMapper;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, CourseRepository courseRepository, AssessmentMapper assessmentMapper) {
        this.assessmentRepository = assessmentRepository;
        this.courseRepository = courseRepository;
        this.assessmentMapper = assessmentMapper;
    }

    @Override
    public AssessmentDTO createAssessment(AssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Creating new assessment for course: {}", assessmentDTO.getCourseId());

        Course course = courseRepository.findById(assessmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + assessmentDTO.getCourseId()));

        Assessment assessment = assessmentMapper.toEntity(assessmentDTO);
        assessment.setCourse(course);
        Assessment savedAssessment = assessmentRepository.save(assessment);

        log.info("Assessment created successfully with id: {}", savedAssessment.getId());
        return assessmentMapper.toDTO(savedAssessment);
    }

    @Override
    @Transactional(readOnly = true)
    public AssessmentDTO getAssessmentById(Long assessmentId) {
        log.info("Fetching assessment with id: {}", assessmentId);
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with id: " + assessmentId));
        return assessmentMapper.toDTO(assessment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentDTO> getAssessmentsByCourseId(String courseId) {
        log.info("Fetching assessments for course: {}", courseId);
        return assessmentRepository.findByCourseId(courseId)
                .stream()
                .map(assessmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentDTO> getAssessmentsByCourseIdAndStatus(String courseId, AssessmentStatus status) {
        log.info("Fetching assessments for course: {} with status: {}", courseId, status);
        return assessmentRepository.findByCourseIdAndStatus(courseId, status)
                .stream()
                .map(assessmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentDTO> getAssessmentsByCourseIdAndWeekNumber(String courseId, Integer weekNumber) {
        log.info("Fetching assessments for course: {} with week number: {}", courseId, weekNumber);
        return assessmentRepository.findByCourseIdAndWeekNumber(courseId, weekNumber)
                .stream()
                .map(assessmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentDTO> getAllAssessments() {
        log.info("Fetching all assessments");
        return assessmentRepository.findAll()
                .stream()
                .map(assessmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentDTO> getAssessmentsByStatus(AssessmentStatus status) {
        log.info("Fetching assessments with status: {}", status);
        return assessmentRepository.findByStatus(status)
                .stream()
                .map(assessmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AssessmentDTO updateAssessment(Long assessmentId, AssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Updating assessment with id: {}", assessmentId);

        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with id: " + assessmentId));

        if (assessmentDTO.getCourseId() != null && !assessmentDTO.getCourseId().equals(assessment.getCourse().getId())) {
            Course course = courseRepository.findById(assessmentDTO.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + assessmentDTO.getCourseId()));
            assessment.setCourse(course);
        }

        assessmentMapper.updateEntityFromDTO(assessmentDTO, assessment);
        Assessment updatedAssessment = assessmentRepository.save(assessment);

        log.info("Assessment updated successfully with id: {}", updatedAssessment.getId());
        return assessmentMapper.toDTO(updatedAssessment);
    }

    @Override
    public void deleteAssessment(Long assessmentId) {
        log.info("Deleting assessment with id: {}", assessmentId);

        if (!assessmentRepository.existsById(assessmentId)) {
            throw new ResourceNotFoundException("Assessment not found with id: " + assessmentId);
        }

        assessmentRepository.deleteById(assessmentId);
        log.info("Assessment deleted successfully with id: {}", assessmentId);
    }
}
