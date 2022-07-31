package by.it_academy.events_service.service;

import by.it_academy.events_service.dao.api.IFilmDao;
import by.it_academy.events_service.dao.entity.Film;
import by.it_academy.events_service.dto.event.EventDto;
import by.it_academy.events_service.dao.enums.Type;
import by.it_academy.events_service.mappers.ConcertMapper;
import by.it_academy.events_service.mappers.FilmMapper;
import by.it_academy.events_service.service.api.IClassifierService;
import by.it_academy.events_service.service.api.IFilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class FilmService implements IFilmService {
    private final IFilmDao dao;
    private final IClassifierService classifierService;
    private final FilmMapper mapper;
    private final UserHolder holder;

    public FilmService(IFilmDao dao, IClassifierService classifierService, FilmMapper mapper, UserHolder holder) {
        this.dao = dao;
        this.classifierService = classifierService;
        this.mapper = mapper;
        this.holder = holder;
    }

    @Override
    @Transactional
    public Film save(EventDto dto) {
        this.classifierService.isCountry(dto.getCountry());

        Film film = this.mapper.convertToFilm(dto);
        film.setAuthor(this.holder.getUser().getUsername());

        this.dao.save(film);

        return film;
    }

    @Override
    public Page<Film> getAll(Pageable pageable) {
        return this.dao.findAllByType(Type.FILMS, pageable);
    }

    @Override
    public Film get(UUID uuid) {
        return this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой Film");
                });
    }

    @Override
    @Transactional
    public Film update(EventDto dto, UUID uuid, LocalDateTime dtUpdate) {
        this.classifierService.isCountry(dto.getCountry());

        Film film = this.dao.findById(uuid)
                .orElseThrow(() -> {throw new IllegalArgumentException("Не нашли такой Film");
                });

        if (film.getAuthor().equals(this.holder.getUser().getUsername()) ||
                this.holder.isAdmin()) {

            this.mapper.updateFilm(dto, film);
            film.setAuthor(this.holder.getUser().getUsername());

        } else {
            throw new AuthenticationException("Нет прав на редактирование записи!") {
            };
        }

        if (!film.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Данные были изменены! Обновите данные.");
        }

        this.dao.save(film);

        return film;
    }
}
