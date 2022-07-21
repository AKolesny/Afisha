package by.it_academy.users_service.service;

import by.it_academy.users_service.controller.utils.JwtTokenUtil;
import by.it_academy.users_service.dao.IUserDao;
import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import by.it_academy.users_service.service.api.IUserMapper;
import by.it_academy.users_service.service.api.IUserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserDao dao;
    private final UserHolder holder;
    private final IUserMapper mapper;
    private final PasswordEncoder encoder;

    public UserService(IUserDao dao, UserHolder holder, IUserMapper mapper, PasswordEncoder encoder) {
        this.dao = dao;
        this.holder = holder;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public User signUp(UserSignUp dto) {
        User user = this.mapper.mapUser(dto);
        user.setPassword(encoder.encode(user.getPassword()));

        this.dao.save(user);

        return user;
    }

    @Override
    public String logIn(UserLogIn dto) throws AuthenticationException {
        UserDetails user = loadUserByUsername(dto.getMail());

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Неверный пароль") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }

        return JwtTokenUtil.generateAccessToken(user);
    }

    @Override
    public User get() {
        return this.holder.getUser();
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
