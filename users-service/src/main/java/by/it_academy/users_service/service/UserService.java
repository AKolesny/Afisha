package by.it_academy.users_service.service;

import by.it_academy.users_service.dao.IUserDao;
import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import by.it_academy.users_service.service.api.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements IUserService {

    private final IUserDao dao;

    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public void signUp(UserSignUp dto) {

    }

    @Override
    public String logIn(UserLogIn dto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь не найден");
        }
        return user;
    }
}
