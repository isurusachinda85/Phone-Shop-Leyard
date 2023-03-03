package lk.ijse.phoneshop.dto;

public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private String phoneNo;
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String address, String phoneNo, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
