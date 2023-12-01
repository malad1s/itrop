package com.my.enity;

import java.util.Comparator;

public class UserEnity {

    private int id;
    private String surname;
    private String firstname;
    private String email;
    private String password;
    private Role role;
    private Integer presence;

    public Integer getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    public UserEnity(int id, String surname, String firstname, String email, String password, Role role, int presence) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.presence = presence;
    }

    public UserEnity() {
        role=new Role();
    }

    public UserEnity(int id) {
        this.id = id;
        role=new Role();
    }

    public UserEnity(int id, String surname, String firstname, String email, String password, Role role) {
        this.id = id;
        this.surname = surname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public static Comparator<UserEnity> getComparatorCompareByPresense(){
        return (UserEnity o1, UserEnity o2)-> o1.getPresence().compareTo(o2.getPresence());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
