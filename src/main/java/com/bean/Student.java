package com.bean;

public class Student {
    private Integer id;
    private String stuId;
    private String name;
    private String classNumber;
    private String email;
    private String passwd;

    public Student(){}

    public Student(Integer id, String stuId, String name, String classNumber, String email, String passwd){
        this.id=id;
        this.stuId=stuId;
        this.name=name;
        this.classNumber=classNumber;
        this.email=email;
        this.passwd=passwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
