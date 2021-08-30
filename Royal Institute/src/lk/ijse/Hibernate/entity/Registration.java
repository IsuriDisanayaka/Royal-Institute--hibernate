package lk.ijse.Hibernate.entity;

import javax.persistence.*;




@Entity
public class Registration implements SuperEntity{
    @Id
    private int regNo;
    private String regDate;
    private double redFee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne (fetch = FetchType.LAZY)
    private Course course;

    public Registration() {
    }

    public Registration(int regNo, String regDate, double redFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.redFee = redFee;
        this.student = student;
        this.course = course;
    }


    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public double getRedFee() {
        return redFee;
    }

    public void setRedFee(double redFee) {
        this.redFee = redFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate='" + regDate + '\'' +
                ", redFee=" + redFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }

}
