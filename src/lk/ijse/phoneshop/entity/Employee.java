package lk.ijse.phoneshop.entity;

public class Employee {
    private String eid;
    private String name;
    private String address;
    private int phoneNo;
    private String email;
    private String dateOfBirth;
    private String jobRole;
    private String userName;

    public Employee(String eid, String name, String address, int phoneNo, String email, String dateOfBirth, String jobRole, String userName, String password) {
        this.eid = eid;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobRole = jobRole;
        this.userName = userName;
        this.password = password;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
