package by.it_academy.events_service.dao.api;

import by.it_academy.events_service.dao.entity.Concert;
import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dao.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IConcertDao extends JpaRepository<Concert, UUID> {

    Page<Concert> findAllByType(Type type, Pageable pageable);
}
