package by.it_academy.users_service.service;

import by.it_academy.users_service.dao.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserHolder {

    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
