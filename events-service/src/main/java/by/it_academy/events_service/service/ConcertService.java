package by.it_academy.events_service.service;

import by.it_academy.events_service.dao.api.IConcertDao;
import by.it_academy.events_service.dao.entity.Concert;
import by.it_academy.events_service.dto.event.EventDto;
import by.it_academy.events_service.dao.enums.Type;
import by.it_academy.events_service.mappers.ConcertMapper;
import by.it_academy.events_service.service.api.IClassifierService;
import by.it_academy.events_service.service.api.IConcertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ConcertService implements IConcertService {

    private final IConcertDao dao;
    private final IClassifierService classifierService;
    private final UserHolder holder;
    private final ConcertMapper mapper;

    public ConcertService(IConcertDao dao, IClassifierService classifierService, UserHolder holder, ConcertMapper mapper) {
        this.dao = dao;
        this.classifierService = classifierService;
        this.holder = holder;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Concert save(EventDto dto) {
        this.classifierService.isCategory(dto.getCountry());

        Concert concert = this.mapper.convertToConcert(dto);
        concert.setAuthor(this.holder.getUser().getUsername());

        this.dao.save(concert);

        return concert;
    }

    @Override
    public Page<Concert> getAll(Pageable pageable) {
        return this.dao.findAllByType(Type.CONCERTS, pageable);
    }

    @Override
    public Concert get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой CONCERT");
                });
    }

    @Override
    @Transactional
    public Concert update(EventDto dto, UUID uuid, LocalDateTime dtUpdate) {
        this.classifierService.isCategory(dto.getCountry());

        Concert concert = this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой CONCERTS");
                });


        if (concert.getAuthor().equals(this.holder.getUser().getUsername()) ||
                this.holder.isAdmin()) {

            this.mapper.updateConcert(dto, concert);
            concert.setAuthor(this.holder.getUser().getUsername());

        } else {
            throw new AuthenticationException("Нет прав на редактирование записи!") {
            };
        }

        if (!concert.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Данные были изменены! Обновите данные.");
        }

        this.dao.save(concert);

        return concert;
    }
}
