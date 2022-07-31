package by.it_academy.events_service.service.api;

import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dto.event.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IEventService<T> {

    T save(EventDto dto);

    Page<T> getAll(Pageable pageable);

    T get(UUID uuid);

    T update(EventDto dto, UUID uuid, LocalDateTime dtUpdate);
}
