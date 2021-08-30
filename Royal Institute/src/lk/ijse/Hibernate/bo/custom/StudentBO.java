package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.StudentDTO;

import java.util.List;


public interface StudentBO extends SuperBO {
    public boolean addStudent(StudentDTO student)throws Exception;

    public boolean deleteStudent(StudentDTO student)throws Exception;

    public boolean updateStudent(StudentDTO student)throws Exception;

    public StudentDTO  getStudent(String id)throws Exception;

    public List<StudentDTO> getAllStudents()throws Exception;
}
