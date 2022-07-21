package by.it_academy.users_service.service.api;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.dto.UserSignUp;

public interface IUserMapper {
    UserDtoOut mapDtoOut(User user);
    User mapUser(UserSignUp dto);
}
