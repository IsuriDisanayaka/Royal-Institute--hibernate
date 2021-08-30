package lk.ijse.Hibernate.model;


public class StudentTM {
    private String Id;
    private String StudentName;
    private String Address;
    private String Contact;
    private String Date;
    private String Gender;

    public StudentTM() {
    }

    public StudentTM(String id, String studentName, String address, String contact, String date, String gender) {
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

    public String getStudentName() {
        return StudentName;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    public String getDate() {
        return Date;
    }

    public String getGender() {
        return Gender;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "Id='" + Id + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Date='" + Date + '\'' +
                ", Gender='" + Gender + '\'' +
                '}';
    }
}