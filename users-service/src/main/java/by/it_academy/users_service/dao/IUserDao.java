package by.it_academy.users_service.dao;

import by.it_academy.users_service.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserDao extends JpaRepository<User, UUID> {
    User findByMail(String mail);
}
