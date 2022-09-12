package com.example.firebaseconnectivity;

public class storingData {
    String firstName;
    String secondName;
    String email;
    String phoneNo;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public storingData(String username) {
        this.username = username;
    }

    String username;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public storingData(String username, String email,String firstName, String secondName, String phoneNo, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNo = phoneNo;
        this.password = password;
    }
}
