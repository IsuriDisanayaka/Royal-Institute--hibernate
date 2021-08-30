package lk.ijse.Hibernate.dto;

public class StudentDTO {

    private String Id;
    private String StudentName;
    private String Address;
    private String Contact;
    private String Date;
    private String Gender;

    public StudentDTO() {
    }

    public StudentDTO(String id) {
        Id = id;
    }

    public StudentDTO(String id, String studentName, String address, String contact, String date, String gender) {
        Id = id;
        StudentName = studentName;
        Address = address;
        Contact = contact;
        Date = date;
        Gender = gender;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "Id='" + Id + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Date='" + Date + '\'' +
                ", Gender='" + Gender + '\'' +
                '}';
    }
}
