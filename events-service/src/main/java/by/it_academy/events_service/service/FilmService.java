package by.it_academy.events_service.service;

import by.it_academy.events_service.dao.api.IFilmDao;
import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dao.entity.Film;
import by.it_academy.events_service.dto.EventDto;
import by.it_academy.events_service.dto.enums.Type;
import by.it_academy.events_service.service.api.IClassifierService;
import by.it_academy.events_service.service.api.IFilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FilmService implements IFilmService {
    private final IFilmDao dao;
    private final IClassifierService classifierService;

    public FilmService(IFilmDao dao, IClassifierService classifierService) {
        this.dao = dao;
        this.classifierService = classifierService;
    }

    @Override
    public void save(EventDto dto) {
        this.classifierService.isCountry(dto.getCountry());

        Film film = new Film();
        film.setUuid(UUID.randomUUID());
        film.setDtCreate(LocalDateTime.now());
        film.setDtUpdate(film.getDtUpdate());
        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setType(dto.getType());
        film.setStatus(dto.getStatus());
        film.setCountry(dto.getCountry());
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());

        this.dao.save(film);
    }

    @Override
    public Page<Event> getAll(Pageable pageable) {
        return this.dao.findAllByType(Type.FILMS, pageable);
    }

    @Override
    public Event get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой Film");
                });
    }

    @Override
    public void update(EventDto dto, UUID uuid, Long dtUpdate) {
        this.classifierService.isCountry(dto.getCountry());

        Film film = this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой Film");
                });

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setType(dto.getType());
        film.setStatus(dto.getStatus());
        film.setCountry(dto.getCountry());
        film.setReleaseYear(dto.getReleaseYear());
        film.setReleaseDate(dto.getReleaseDate());
        film.setDuration(dto.getDuration());

        if (!film.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Данные были изменены! Обновите данные.");
        }

        this.dao.save(film);
    }
}
