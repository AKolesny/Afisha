package by.it_academy.users_service.dao;

import by.it_academy.users_service.dao.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends JpaRepository<Role, String> {
}
