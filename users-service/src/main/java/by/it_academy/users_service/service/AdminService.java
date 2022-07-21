package by.it_academy.users_service.service;

import by.it_academy.users_service.dao.IUserDao;
import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.service.api.IAdminMapper;
import by.it_academy.users_service.service.api.IAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AdminService implements IAdminService {

    private final IUserDao dao;
    private final IAdminMapper mapper;
    private final PasswordEncoder encoder;

    public AdminService(IUserDao dao, IAdminMapper mapper, PasswordEncoder encoder) {
        this.dao = dao;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public User save(UserDto dto) {
        User user = this.mapper.mapUser(dto);

        user.setPassword(encoder.encode(user.getPassword()));

        this.dao.save(user);

        return user;
    }

    @Override
    public User get(UUID uuid) {

        return this.dao.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Нет такого user"));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {

        return this.dao.findAll(pageable);
    }

    @Override
    public User update(UserDto dto, UUID uuid, LocalDateTime dtUpdate) {
        User user = this.dao.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Нет такого user"));

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getDtUpdate().equals(dtUpdate)) {
            this.dao.save(user);
        } else {
            throw new OptimisticLockException("User был обновлен, обновите данные");
        }

        return user;
    }
}
