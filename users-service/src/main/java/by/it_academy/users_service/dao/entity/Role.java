package by.it_academy.users_service.dao.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "", name = "Role")
public class Role implements GrantedAuthority {


    private String name;
    private List<User> users;

    public Role() {
    }

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
