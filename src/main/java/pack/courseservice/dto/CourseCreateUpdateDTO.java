package pack.courseservice.dto;

public class CourseCreateUpdateDTO {

    private String courseCode;
    private String moduleCode;
    private String presentationLength;
    private String title;
    private String description;
    private pack.courseservice.entity.CourseStatus status;

    public CourseCreateUpdateDTO() {}

    public CourseCreateUpdateDTO(String courseCode, String moduleCode, String presentationLength, String title, String description, pack.courseservice.entity.CourseStatus status) {
        this.courseCode = courseCode;
        this.moduleCode = moduleCode;
        this.presentationLength = presentationLength;
        this.title = title;
        this.description = description;
        this.status = status;
    }

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
}
