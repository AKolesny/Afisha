package by.it_academy.events_service.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserHolder {
    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isAdmin() {
        Collection<? extends GrantedAuthority> authorities = getUser().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if ("ADMIN".equals(authority.getAuthority())) {
                return true;
            }
        }
        return false;
    }
}
