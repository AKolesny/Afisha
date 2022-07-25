package by.it_academy.users_service.service.api;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

@Validated
public interface IUserService extends UserDetailsService{
    User signUp(UserSignUp dto);
    String logIn(UserLogIn dto);
    User get();
}
