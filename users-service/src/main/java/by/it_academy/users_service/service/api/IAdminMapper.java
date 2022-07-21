package by.it_academy.users_service.service.api;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.IMyPage;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.dto.UserDtoOut;
import org.springframework.data.domain.Page;

public interface IAdminMapper {
    UserDtoOut mapDtoOut(User user);
    IMyPage<UserDtoOut> mapPage(Page<User> page);

    User mapUser(UserDto dto);
}
