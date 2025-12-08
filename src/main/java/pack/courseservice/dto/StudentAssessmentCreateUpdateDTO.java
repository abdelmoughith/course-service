package pack.courseservice.dto;

public class StudentAssessmentCreateUpdateDTO {

    private Long studentId;
    private Double marksObtained;
    private pack.courseservice.entity.SubmissionStatus submissionStatus;
    private java.time.LocalDateTime submittedAt;
    private java.time.LocalDateTime gradedAt;
    private String feedback;
    private Long assessmentId;

    public StudentAssessmentCreateUpdateDTO() {}

    public StudentAssessmentCreateUpdateDTO(Long studentId, Double marksObtained, pack.courseservice.entity.SubmissionStatus submissionStatus, java.time.LocalDateTime submittedAt, java.time.LocalDateTime gradedAt, String feedback, Long assessmentId) {
        this.studentId = studentId;
        this.marksObtained = marksObtained;
        this.submissionStatus = submissionStatus;
        this.submittedAt = submittedAt;
        this.gradedAt = gradedAt;
        this.feedback = feedback;
        this.assessmentId = assessmentId;
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Double getMarksObtained() { return marksObtained; }
    public void setMarksObtained(Double marksObtained) { this.marksObtained = marksObtained; }

    public pack.courseservice.entity.SubmissionStatus getSubmissionStatus() { return submissionStatus; }
    public void setSubmissionStatus(pack.courseservice.entity.SubmissionStatus submissionStatus) { this.submissionStatus = submissionStatus; }

    public java.time.LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(java.time.LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

    public java.time.LocalDateTime getGradedAt() { return gradedAt; }
    public void setGradedAt(java.time.LocalDateTime gradedAt) { this.gradedAt = gradedAt; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Long getAssessmentId() { return assessmentId; }
    public void setAssessmentId(Long assessmentId) { this.assessmentId = assessmentId; }
}
