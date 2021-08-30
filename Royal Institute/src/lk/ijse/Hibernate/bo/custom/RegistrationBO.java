package lk.ijse.Hibernate.bo.custom;

import lk.ijse.Hibernate.bo.SuperBO;
import lk.ijse.Hibernate.dto.RegistrationDTO;


import java.util.List;

public interface RegistrationBO extends SuperBO {
    public boolean addRegistration(RegistrationDTO registration)throws Exception;

    public boolean deleteRegistration(RegistrationDTO registration)throws Exception;

    public boolean updateRegistration(RegistrationDTO registration)throws Exception;

    public RegistrationDTO getRegistration(String id)throws Exception;

    public List<RegistrationDTO> getAllRegistrations()throws Exception;
}
