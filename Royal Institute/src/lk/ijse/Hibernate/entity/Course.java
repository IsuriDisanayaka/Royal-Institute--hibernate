package lk.ijse.Hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity {
    @Id
    private String CId;
    private String CourseName;
    private String CourseType;
    private String Duration;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Registration>registrations;

    public Course() {
    }

    public Course(String CId) {
        this.CId = CId;
    }

    public Course(String CId, String courseName, String courseType, String duration) {
        this.CId = CId;
        CourseName = courseName;
        CourseType = courseType;
        Duration = duration;
    }

    public Course(List<Registration> registrations) {
        this.registrations = registrations;
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
        return "Course{" +
                "CId='" + CId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", CourseType='" + CourseType + '\'' +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}