package by.it_academy.events_service.service.api;

import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dto.EventDto;
import by.it_academy.events_service.dto.EventDtoOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IEventService {

    void save(EventDto dto);

    Page<Event> getAll(Pageable pageable);

    Event get(UUID uuid);

    void update(EventDto dto, UUID uuid, Long dtUpdate);
}
