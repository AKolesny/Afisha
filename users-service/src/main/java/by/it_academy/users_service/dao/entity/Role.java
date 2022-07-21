package by.it_academy.users_service.dao.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(schema = "user", name = "roles")
public class Role implements GrantedAuthority {


    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
