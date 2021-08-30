package lk.ijse.Hibernate.dao;

import lk.ijse.Hibernate.dao.custom.impl.CourseDAOImpl;
import lk.ijse.Hibernate.dao.custom.impl.RegistrationDAOImpl;
import lk.ijse.Hibernate.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (null == daoFactory ) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case COURSE:
                return (T)new CourseDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
