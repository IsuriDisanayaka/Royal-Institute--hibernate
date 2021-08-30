package lk.ijse.Hibernate.bo.custom.impl;

import lk.ijse.Hibernate.bo.custom.RegistrationBO;
import lk.ijse.Hibernate.dao.DAOFactory;
import lk.ijse.Hibernate.dao.DAOType;
import lk.ijse.Hibernate.dao.custom.RegistrationDAO;
import lk.ijse.Hibernate.dao.custom.StudentDAO;
import lk.ijse.Hibernate.dto.CourseDTO;
import lk.ijse.Hibernate.dto.RegistrationDTO;
import lk.ijse.Hibernate.dto.StudentDTO;
import lk.ijse.Hibernate.entity.Course;
import lk.ijse.Hibernate.entity.Registration;
import lk.ijse.Hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);
    @Override
    public boolean addRegistration(RegistrationDTO registration) throws Exception {
        StudentDTO studentDTO = registration.getStudentDTO();
        Student student = new Student(studentDTO.getId(),
                                     studentDTO.getStudentName(),
                                      studentDTO.getAddress(),
                                      studentDTO.getContact(),
                                        studentDTO.getDate(),
                                          studentDTO.getGender());
        CourseDTO courseDTO = registration.getCourseDTO();
        Course course = new Course(courseDTO.getCId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration());
        return registrationDAO.add(new Registration(registration.getRegNo(),
                registration.getRegDate(),
                registration.getRegFee(),
                student,course));
    }

    @Override
    public boolean deleteRegistration(RegistrationDTO registration) throws Exception {
        StudentDTO studentDTO = registration.getStudentDTO();
        Student student = new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDate(),
                studentDTO.getGender());
        CourseDTO courseDTO = registration.getCourseDTO();
        Course course = new Course(courseDTO.getCId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration());
        return registrationDAO.delete(new Registration(registration.getRegNo(),
                registration.getRegDate(),
                registration.getRegFee(),
                student,course));
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registration) throws Exception {
        StudentDTO studentDTO = registration.getStudentDTO();
        Student student = new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDate(),
                studentDTO.getGender());
        CourseDTO courseDTO = registration.getCourseDTO();
        Course course = new Course(courseDTO.getCId(),
                courseDTO.getCourseName(),
                courseDTO.getCourseType(),
                courseDTO.getDuration());
        return registrationDAO.update(new Registration(registration.getRegNo(),
                registration.getRegDate(),
                registration.getRegFee(),
                student,course));
    }

    @Override
    public RegistrationDTO getRegistration(String id) throws Exception {
        Registration r=registrationDAO.getOne(id);
        return new RegistrationDTO(
                r.getRegNo(),
                r.getRegDate(),
                r.getRedFee(),
                r.getStudent(),
                r.getCourse());
    }

    @Override
    public List<RegistrationDTO> getAllRegistrations() throws Exception {
        List<Registration>registrationList = registrationDAO.getAll();

        List<RegistrationDTO> registrationDTOList = new ArrayList<>();

        for (Registration registration: registrationList) {
            registrationDTOList.add(new RegistrationDTO(
                    registration.getRegNo(),
                   registration.getRegDate(),
                   registration.getRedFee(),
                   registration.getStudent(),
                   registration.getCourse()));
        }
        return registrationDTOList;
    }

}
