package by.it_academy.users_service.service.api;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAdminService {
    User save (UserDto dto);
    User get (UUID uuid);
    Page<User> getAll(Pageable pageable);
    User update(UserDto dto, UUID uuid, LocalDateTime dtUpdate);

}
