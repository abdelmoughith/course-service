package pack.courseservice.dto;

import pack.courseservice.entity.AssessmentStatus;
import pack.courseservice.entity.AssessmentType;

public class AssessmentCreateUpdateDTO {

    private String title;
    private String description;
    private AssessmentType assessmentType;
    private Double maxMarks;
    private Integer durationMinutes;
    private AssessmentStatus status;
    private Integer weekNumber;
    private String courseId;

    public AssessmentCreateUpdateDTO() {}

    public AssessmentCreateUpdateDTO(String title, String description, AssessmentType assessmentType, Double maxMarks, Integer durationMinutes, AssessmentStatus status, Integer weekNumber, String courseId) {
        this.title = title;
        this.description = description;
        this.assessmentType = assessmentType;
        this.maxMarks = maxMarks;
        this.durationMinutes = durationMinutes;
        this.status = status;
        this.weekNumber = weekNumber;
        this.courseId = courseId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public AssessmentType getAssessmentType() { return assessmentType; }
    public void setAssessmentType(AssessmentType assessmentType) { this.assessmentType = assessmentType; }

    public Double getMaxMarks() { return maxMarks; }
    public void setMaxMarks(Double maxMarks) { this.maxMarks = maxMarks; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }

    public AssessmentStatus getStatus() { return status; }
    public void setStatus(AssessmentStatus status) { this.status = status; }

    public Integer getWeekNumber() { return weekNumber; }
    public void setWeekNumber(Integer weekNumber) { this.weekNumber = weekNumber; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}
