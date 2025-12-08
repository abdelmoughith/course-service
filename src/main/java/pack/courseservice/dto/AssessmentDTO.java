package pack.courseservice.dto;

public class AssessmentDTO {

    private Long id;
    private String title;
    private String description;
    private pack.courseservice.entity.AssessmentType assessmentType;
    private Double maxMarks;
    private Integer durationMinutes;
    private pack.courseservice.entity.AssessmentStatus status;
    private Integer weekNumber;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private String courseId;

    public AssessmentDTO() {}

    public AssessmentDTO(Long id, String title, String description, pack.courseservice.entity.AssessmentType assessmentType, Double maxMarks, Integer durationMinutes, pack.courseservice.entity.AssessmentStatus status, Integer weekNumber, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, String courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assessmentType = assessmentType;
        this.maxMarks = maxMarks;
        this.durationMinutes = durationMinutes;
        this.status = status;
        this.weekNumber = weekNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.courseId = courseId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public pack.courseservice.entity.AssessmentType getAssessmentType() { return assessmentType; }
    public void setAssessmentType(pack.courseservice.entity.AssessmentType assessmentType) { this.assessmentType = assessmentType; }

    public Double getMaxMarks() { return maxMarks; }
    public void setMaxMarks(Double maxMarks) { this.maxMarks = maxMarks; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public pack.courseservice.entity.AssessmentStatus getStatus() { return status; }
    public void setStatus(pack.courseservice.entity.AssessmentStatus status) { this.status = status; }

    public Integer getWeekNumber() { return weekNumber; }
    public void setWeekNumber(Integer weekNumber) { this.weekNumber = weekNumber; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}
