package pack.courseservice.dto;

public class VLEMaterialCreateUpdateDTO {

    private String title;
    private String description;
    private pack.courseservice.entity.MaterialType materialType;
    private String contentUrl;
    private pack.courseservice.entity.MaterialStatus status;
    private Integer weekNumber;
    private String courseId;

    public VLEMaterialCreateUpdateDTO() {}

    public VLEMaterialCreateUpdateDTO(String title, String description, pack.courseservice.entity.MaterialType materialType, String contentUrl, pack.courseservice.entity.MaterialStatus status, Integer weekNumber, String courseId) {
        this.title = title;
        this.description = description;
        this.materialType = materialType;
        this.contentUrl = contentUrl;
        this.status = status;
        this.weekNumber = weekNumber;
        this.courseId = courseId;
    }

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

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}
