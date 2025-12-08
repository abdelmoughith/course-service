package pack.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.courseservice.entity.StudentAssessment;
import pack.courseservice.entity.SubmissionStatus;

import java.util.List;

@Repository
public interface StudentAssessmentRepository extends JpaRepository<StudentAssessment, Long> {

    List<StudentAssessment> findByStudentId(Long studentId);

    List<StudentAssessment> findByAssessmentId(Long assessmentId);

    List<StudentAssessment> findByStudentIdAndSubmissionStatus(Long studentId, SubmissionStatus status);

    List<StudentAssessment> findByAssessmentIdAndSubmissionStatus(Long assessmentId, SubmissionStatus status);
}

