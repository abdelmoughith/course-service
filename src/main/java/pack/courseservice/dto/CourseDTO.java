package pack.courseservice.dto;

public class CourseDTO {

    private String id;
    private String courseCode;
    private String moduleCode;
    private String presentationLength;
    private String title;
    private String description;
    private pack.courseservice.entity.CourseStatus status;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private java.util.List<VLEMaterialDTO> vleMaterials;
    private java.util.List<AssessmentDTO> assessments;

    public CourseDTO() {}

    public CourseDTO(String id, String courseCode, String moduleCode, String presentationLength, String title, String description, pack.courseservice.entity.CourseStatus status, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, java.util.List<VLEMaterialDTO> vleMaterials, java.util.List<AssessmentDTO> assessments) {
        this.id = id;
        this.courseCode = courseCode;
        this.moduleCode = moduleCode;
        this.presentationLength = presentationLength;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.vleMaterials = vleMaterials;
        this.assessments = assessments;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getModuleCode() { return moduleCode; }
    public void setModuleCode(String moduleCode) { this.moduleCode = moduleCode; }

    public String getPresentationLength() { return presentationLength; }
    public void setPresentationLength(String presentationLength) { this.presentationLength = presentationLength; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public pack.courseservice.entity.CourseStatus getStatus() { return status; }
    public void setStatus(pack.courseservice.entity.CourseStatus status) { this.status = status; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public java.util.List<VLEMaterialDTO> getVleMaterials() { return vleMaterials; }
    public void setVleMaterials(java.util.List<VLEMaterialDTO> vleMaterials) { this.vleMaterials = vleMaterials; }

    public java.util.List<AssessmentDTO> getAssessments() { return assessments; }
    public void setAssessments(java.util.List<AssessmentDTO> assessments) { this.assessments = assessments; }
}
