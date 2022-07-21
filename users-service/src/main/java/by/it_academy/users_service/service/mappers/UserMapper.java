package by.it_academy.users_service.service.mappers;

import by.it_academy.users_service.dao.entity.Role;
import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dao.enums.Status;
import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.dto.UserSignUp;
import by.it_academy.users_service.service.api.IUserMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Component
public class UserMapper implements IUserMapper {

    public UserDtoOut mapDtoOut(User user) {
        UserDtoOut dto = new UserDtoOut();

        dto.setUuid(user.getUuid());
        dto.setDtCreate(user.getDtCreate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setMail(user.getMail());
        dto.setNick(user.getNick());
        dto.setStatus(user.getStatus());
        dto.setRole(user.getRole());

        return dto;
    }

    public User mapUser(UserSignUp dto) {
        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtUpdate());
        user.setStatus(Status.WAITING_ACTIVATION);
        user.setRole(Set.of(new Role("USER")));
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setPassword(dto.getPassword());

        return user;
    }
}
