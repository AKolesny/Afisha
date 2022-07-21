package by.it_academy.users_service.dto;

import by.it_academy.users_service.dao.entity.Role;
import by.it_academy.users_service.dao.enums.Status;

import java.util.Set;

public class UserDto {

    private String mail;
    private String password;
    private String nick;
    private Set<Role> role;
    private Status status;

    public UserDto() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
