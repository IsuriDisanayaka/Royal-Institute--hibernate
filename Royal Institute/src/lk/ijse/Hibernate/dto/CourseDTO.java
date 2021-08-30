package lk.ijse.Hibernate.dto;

public class CourseDTO {
    private String CId;
    private String CourseName;
    private String CourseType;
    private String Duration;

    public CourseDTO() {
    }

    public CourseDTO(String CId) {
        this.CId = CId;
    }

    public CourseDTO(String CId, String courseName, String courseType, String duration) {
        this.CId = CId;
        CourseName = courseName;
        CourseType = courseType;
        Duration = duration;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "CId='" + CId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", CourseType='" + CourseType + '\'' +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}
