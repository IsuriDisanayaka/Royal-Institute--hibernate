package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.CourseDTO;

import java.util.List;

public interface CourseBO extends SuperBO {
    public boolean addCourse(CourseDTO course)throws Exception;

    public boolean deleteCourse(CourseDTO  course)throws Exception;

    public boolean updateCourse(CourseDTO course)throws Exception;

    public CourseDTO getCourse(String id)throws Exception;

    public List<CourseDTO> getAllCourses()throws Exception;
}
