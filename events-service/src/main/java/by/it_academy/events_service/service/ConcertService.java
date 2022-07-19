package by.it_academy.events_service.service;

import by.it_academy.events_service.dao.api.IConcertDao;
import by.it_academy.events_service.dao.entity.Concert;
import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dto.EventDto;
import by.it_academy.events_service.dto.enums.Type;
import by.it_academy.events_service.service.api.IClassifierService;
import by.it_academy.events_service.service.api.IConcertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConcertService implements IConcertService {

    private final IConcertDao dao;
    private final IClassifierService classifierService;

    public ConcertService(IConcertDao dao, IClassifierService classifierService) {
        this.dao = dao;
        this.classifierService = classifierService;
    }

    @Override
    public void save(EventDto dto) {
        this.classifierService.isCountry(dto.getCountry());

        Concert concert = new Concert();
        concert.setUuid(UUID.randomUUID());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(concert.getDtUpdate());
        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setStatus(dto.getStatus());
        concert.setCategory(dto.getCountry());

        this.dao.save(concert);
    }

    @Override
    public Page<Event> getAll(Pageable pageable) {
        return this.dao.findAllByType(Type.CONCERTS, pageable);
    }

    @Override
    public Event get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой CONCERTS");
                });
    }

    @Override
    public void update(EventDto dto, UUID uuid, Long dtUpdate) {
        this.classifierService.isCountry(dto.getCountry());

        Concert concert = this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой CONCERTS");
                });

        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setStatus(dto.getStatus());
        concert.setCategory(dto.getCountry());

        if (!concert.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Данные были изменены! Обновите данные.");
        }

        this.dao.save(concert);
    }
}
