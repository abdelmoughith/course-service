package pack.courseservice.dto;

public class VLEMaterialDTO {

    private Long id;
    private String title;
    private String description;
    private pack.courseservice.entity.MaterialType materialType;
    private String contentUrl;
    private pack.courseservice.entity.MaterialStatus status;
    private Integer weekNumber;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
    private String courseId;

    public VLEMaterialDTO() {}

    public VLEMaterialDTO(Long id, String title, String description, pack.courseservice.entity.MaterialType materialType, String contentUrl, pack.courseservice.entity.MaterialStatus status, Integer weekNumber, java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, String courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.materialType = materialType;
        this.contentUrl = contentUrl;
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

    public pack.courseservice.entity.MaterialType getMaterialType() { return materialType; }
    public void setMaterialType(pack.courseservice.entity.MaterialType materialType) { this.materialType = materialType; }

    public String getContentUrl() { return contentUrl; }
    public void setContentUrl(String contentUrl) { this.contentUrl = contentUrl; }

    public pack.courseservice.entity.MaterialStatus getStatus() { return status; }
    public void setStatus(pack.courseservice.entity.MaterialStatus status) { this.status = status; }

    public Integer getWeekNumber() { return weekNumber; }
    public void setWeekNumber(Integer weekNumber) { this.weekNumber = weekNumber; }

    public java.time.LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(java.time.LocalDateTime createdAt) { this.createdAt = createdAt; }

    public java.time.LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(java.time.LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}
