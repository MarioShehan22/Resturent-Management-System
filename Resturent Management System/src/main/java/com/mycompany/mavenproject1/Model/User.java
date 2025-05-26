package com.mycompany.mavenproject1.Model;

import java.util.Date;

public class User {
    private String userName;
    private String email;
    private String phone;
    private Date dob;
    private String password;

    public User() {
    }

    public User(String userName, String email, String phone, Date dob, String password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
