package com.example.nikhil.krigerapplication.model;

/**
 * Created by nikhil on 3/11/17.
 */

public class User {

    String email ;
    String password ;
    private String ph;
    private String gender;
    private String city;

    public User() {}

    public User(String email, String password, String ph, String gender, String city) {
        this.email = email;
        this.password = password;
        this.ph = ph;
        this.gender = gender;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ph='" + ph + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
