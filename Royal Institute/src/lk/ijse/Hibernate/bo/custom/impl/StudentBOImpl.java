package lk.ijse.Hibernate.bo.custom.impl;


import lk.ijse.Hibernate.bo.custom.StudentBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.StudentDAO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;


public class StudentBOImpl implements StudentBO {
   StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);


    @Override
    public boolean addStudent(StudentDTO student) throws Exception {
//        System.out.println("bo"+student.toString());
//        Student student1=new Student(
//                student.getId(),
//                student.getStudentName(),
//                student.getAddress(),
//                student.getContact(),
//                student.getDate(),
//                student.getGender()
//        );
//        System.out.println("check"+student1);

        return studentDAO.add(new Student(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDate(),
                student.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(StudentDTO student) throws Exception {
        return studentDAO.delete(new Student(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDate(),
                student.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
       return studentDAO.update(new Student(
                student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDate(),
                student.getGender()));
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student s = studentDAO.getOne(id);
        return new StudentDTO(
                s.getId(),
                s.getStudentName(),
                s.getAddress(),
                s.getContact(),
                s.getDate(),
                s.getGender());
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student>studentList = studentDAO.getAll();

        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student: studentList) {
           studentDTOList.add(new StudentDTO(
                   student.getId(),
                   student.getStudentName(),
                   student.getAddress(),
                   student.getContact(),
                   student.getDate(),
                   student.getGender()));
        }
        return studentDTOList;
    }
}

