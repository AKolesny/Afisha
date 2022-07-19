package by.it_academy.users_service.service;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.service.api.IAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService implements IAdminService {
    @Override
    public void save(UserDto dto) {

    }

    @Override
    public User get(UUID uuid) {
        return null;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(UserDto dto, UUID uuid, Long dtUpdate) {

    }
}
