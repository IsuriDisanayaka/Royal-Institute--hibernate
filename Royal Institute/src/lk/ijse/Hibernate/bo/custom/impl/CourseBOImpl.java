package lk.ijse.Hibernate.bo.custom.impl;

import lk.ijse.Hibernate.bo.custom.CourseBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.CourseDAO;
import lk.ijse.Hibernate.dto.CourseDTO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Course;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    @Override
    public boolean addCourse(CourseDTO course) throws Exception {
        System.out.println("bo"+course.toString());
       return courseDAO.add(new Course(
               course.getCId(),
               course.getCourseName(),
               course.getCourseType(),
               course.getDuration()
       ));
    }

    @Override
    public boolean deleteCourse(CourseDTO course) throws Exception {
        return courseDAO.delete(new Course(
                course.getCId(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        ));
    }

    @Override
    public boolean updateCourse(CourseDTO course) throws Exception {
        return courseDAO.update(new Course(
                course.getCId(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDuration()
        ));
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course c = courseDAO.getOne(id);
        return new CourseDTO(
                c.getCId(),
                c.getCourseName(),
                c.getCourseType(),
                c.getDuration());
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        List<Course>coursetList =courseDAO.getAll();

        List<CourseDTO> courseDTOList = new ArrayList<>();

        for (Course course:coursetList) {
            courseDTOList.add(new CourseDTO(
                    course.getCId(),
                    course.getCourseName(),
                    course.getCourseType(),
                    course.getDuration()));

        }
        return courseDTOList;
    }
}
