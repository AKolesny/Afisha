package by.it_academy.classifier_service.dto.security;

import org.springframework.security.core.GrantedAuthority;


public class RoleDto implements GrantedAuthority {


    private String name;

    public RoleDto() {
    }


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
