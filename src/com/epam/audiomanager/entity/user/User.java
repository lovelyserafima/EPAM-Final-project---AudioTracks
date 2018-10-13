package com.epam.audiomanager.entity.user;

import com.epam.audiomanager.entity.Entity;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private TypeUser type;
    private String firstName;
    private String secondName;
    private String email;

    public User(){super();}

    public User(int id, String login){
        super(id);
        this.login = login;
    }

    public User(String login, TypeUser type, String firstName, String secondName, String email){
        this.login = login;
        this.type = type;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public User(int id, String login, TypeUser type, String firstName, String secondName, String email) {
        super(id);
        this.login = login;
        this.type = type;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                getType() == user.getType() &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSecondName(), user.getSecondName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLogin(), getType(), getFirstName(), getSecondName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
