package by.it_academy.users_service.service.api;

import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void signUp(UserSignUp dto);
    String logIn(UserLogIn dto);
}
