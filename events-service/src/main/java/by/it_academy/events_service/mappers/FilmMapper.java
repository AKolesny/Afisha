package by.it_academy.events_service.mappers;

import by.it_academy.events_service.dao.entity.Film;
import by.it_academy.events_service.dto.api.IEvent;
import by.it_academy.events_service.dto.api.IMyPage;
import by.it_academy.events_service.dto.event.EventDto;
import by.it_academy.events_service.dto.event.FilmOutDto;
import by.it_academy.events_service.dto.page.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FilmMapper {


    public FilmOutDto convertToFilmDto(Film film) {
        FilmOutDto dto = new FilmOutDto();

        dto.setUuid(film.getUuid());
        dto.setDtCreate(film.getDtCreate());
        dto.setDtUpdate(film.getDtUpdate());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setDtEvent(film.getDtEvent());
        dto.setDtEndOfSale(film.getDtEndOfSale());
        dto.setType(film.getType());
        dto.setStatus(film.getStatus());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setReleaseDate(film.getReleaseDate());
        dto.setDuration(film.getDuration());
        dto.setAuthor(film.getAuthor());

        return dto;
    }

    public IMyPage<IEvent> convertToFilmPage(Page<Film> page) {
        MyPage<IEvent> myPage = new MyPage<>();

        myPage.setNumber(page.getNumber());
        myPage.setSize(page.getSize());
        myPage.setTotalPages(page.getTotalPages());
        myPage.setTotalElements(page.getTotalElements());
        myPage.setFirst(page.isFirst());
        myPage.setNumberOfElements(page.getNumberOfElements());
        myPage.setLast(page.isLast());

        List<IEvent> content = new ArrayList<>();
        for (Film film : page.getContent()) {
            content.add(convertToFilmDto(film));
        }

        myPage.setContent(content);

        return myPage;
    }

    public Film convertToFilm(EventDto dto) {

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

        return film;
    }

    public void updateFilm(EventDto dto, Film film) {

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

    }



}
