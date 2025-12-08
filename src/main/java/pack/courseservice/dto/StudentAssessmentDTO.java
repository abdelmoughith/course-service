package pack.courseservice.dto;

public class StudentAssessmentDTO {

    private Long id;
    private Long studentId;
    private Double marksObtained;
    private pack.courseservice.entity.SubmissionStatus submissionStatus;
    private java.time.LocalDateTime submittedAt;
    private java.time.LocalDateTime gradedAt;
    private String feedback;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private Long assessmentId;

    public StudentAssessmentDTO() {}

    public StudentAssessmentDTO(Long id, Long studentId, Double marksObtained, pack.courseservice.entity.SubmissionStatus submissionStatus, java.time.LocalDateTime submittedAt, java.time.LocalDateTime gradedAt, String feedback, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, Long assessmentId) {
        this.id = id;
        this.studentId = studentId;
        this.marksObtained = marksObtained;
        this.submissionStatus = submissionStatus;
        this.submittedAt = submittedAt;
        this.gradedAt = gradedAt;
        this.feedback = feedback;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assessmentId = assessmentId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Long getAssessmentId() { return assessmentId; }
    public void setAssessmentId(Long assessmentId) { this.assessmentId = assessmentId; }
}
