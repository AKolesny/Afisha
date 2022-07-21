package by.it_academy.users_service.service.mappers;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.*;
import by.it_academy.users_service.service.api.IAdminMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminMapper implements IAdminMapper {

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

    public IMyPage<UserDtoOut> mapPage(Page<User> page) {
        List<UserDtoOut> userDto = new ArrayList<>();

        for (User user : page.getContent()) {
            userDto.add(mapDtoOut(user));
        }

        MyPage<UserDtoOut> pageDto = new MyPage<>();
        pageDto.setNumber(page.getNumber());
        pageDto.setSize(page.getSize());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setFirst(page.isFirst());
        pageDto.setLast(page.isLast());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setContent(userDto);

        return pageDto;
    }

    public User mapUser(UserDto dto) {
        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setDtCreate(LocalDateTime.now());
        user.setDtUpdate(user.getDtUpdate());
        user.setStatus(dto.getStatus());
        user.setRole(dto.getRole());
        user.setMail(dto.getMail());
        user.setNick(dto.getNick());
        user.setPassword(dto.getPassword());

        return user;
    }
}
