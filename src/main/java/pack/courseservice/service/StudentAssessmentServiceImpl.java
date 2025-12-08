package pack.courseservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pack.courseservice.dto.StudentAssessmentCreateUpdateDTO;
import pack.courseservice.dto.StudentAssessmentDTO;
import pack.courseservice.entity.Assessment;
import pack.courseservice.entity.StudentAssessment;
import pack.courseservice.entity.SubmissionStatus;
import pack.courseservice.exception.ResourceNotFoundException;
import pack.courseservice.mapper.StudentAssessmentMapper;
import pack.courseservice.repository.AssessmentRepository;
import pack.courseservice.repository.StudentAssessmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentAssessmentServiceImpl implements StudentAssessmentService {

    private static final Logger log = LoggerFactory.getLogger(StudentAssessmentServiceImpl.class);
    private final StudentAssessmentRepository studentAssessmentRepository;
    private final AssessmentRepository assessmentRepository;
    private final StudentAssessmentMapper mapper;

    public StudentAssessmentServiceImpl(StudentAssessmentRepository studentAssessmentRepository, AssessmentRepository assessmentRepository, StudentAssessmentMapper mapper) {
        this.studentAssessmentRepository = studentAssessmentRepository;
        this.assessmentRepository = assessmentRepository;
        this.mapper = mapper;
    }

    @Override
    public StudentAssessmentDTO createStudentAssessment(StudentAssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Creating new student assessment for assessment: {}", assessmentDTO.getAssessmentId());

        Assessment assessment = assessmentRepository.findById(assessmentDTO.getAssessmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with id: " + assessmentDTO.getAssessmentId()));

        StudentAssessment studentAssessment = mapper.toEntity(assessmentDTO);
        studentAssessment.setAssessment(assessment);
        StudentAssessment savedAssessment = studentAssessmentRepository.save(studentAssessment);

        log.info("Student assessment created successfully with id: {}", savedAssessment.getId());
        return mapper.toDTO(savedAssessment);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentAssessmentDTO getStudentAssessmentById(Long id) {
        log.info("Fetching student assessment with id: {}", id);
        StudentAssessment assessment = studentAssessmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student assessment not found with id: " + id));
        return mapper.toDTO(assessment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentAssessmentDTO> getStudentAssessmentsByStudentId(Long studentId) {
        log.info("Fetching student assessments for student: {}", studentId);
        return studentAssessmentRepository.findByStudentId(studentId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentAssessmentDTO> getStudentAssessmentsByAssessmentId(Long assessmentId) {
        log.info("Fetching student assessments for assessment: {}", assessmentId);
        return studentAssessmentRepository.findByAssessmentId(assessmentId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentAssessmentDTO> getStudentAssessmentsByStudentIdAndStatus(Long studentId, SubmissionStatus status) {
        log.info("Fetching student assessments for student: {} with status: {}", studentId, status);
        return studentAssessmentRepository.findByStudentIdAndSubmissionStatus(studentId, status)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentAssessmentDTO> getStudentAssessmentsByAssessmentIdAndStatus(Long assessmentId, SubmissionStatus status) {
        log.info("Fetching student assessments for assessment: {} with status: {}", assessmentId, status);
        return studentAssessmentRepository.findByAssessmentIdAndSubmissionStatus(assessmentId, status)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentAssessmentDTO> getAllStudentAssessments() {
        log.info("Fetching all student assessments");
        return studentAssessmentRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentAssessmentDTO updateStudentAssessment(Long id, StudentAssessmentCreateUpdateDTO assessmentDTO) {
        log.info("Updating student assessment with id: {}", id);

        StudentAssessment studentAssessment = studentAssessmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student assessment not found with id: " + id));

        if (assessmentDTO.getAssessmentId() != null && !assessmentDTO.getAssessmentId().equals(studentAssessment.getAssessment().getId())) {
            Assessment assessment = assessmentRepository.findById(assessmentDTO.getAssessmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assessment not found with id: " + assessmentDTO.getAssessmentId()));
            studentAssessment.setAssessment(assessment);
        }

        mapper.updateEntityFromDTO(assessmentDTO, studentAssessment);
        StudentAssessment updatedAssessment = studentAssessmentRepository.save(studentAssessment);

        log.info("Student assessment updated successfully with id: {}", updatedAssessment.getId());
        return mapper.toDTO(updatedAssessment);
    }

    @Override
    public void deleteStudentAssessment(Long id) {
        log.info("Deleting student assessment with id: {}", id);

        if (!studentAssessmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student assessment not found with id: " + id);
        }

        studentAssessmentRepository.deleteById(id);
        log.info("Student assessment deleted successfully with id: {}", id);
    }
}
