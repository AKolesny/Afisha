package by.it_academy.events_service.controller;

import by.it_academy.events_service.dto.api.IEvent;
import by.it_academy.events_service.dto.api.IMyPage;
import by.it_academy.events_service.dto.event.EventDto;
import by.it_academy.events_service.dao.enums.Type;
import by.it_academy.events_service.mappers.ConcertMapper;
import by.it_academy.events_service.mappers.FilmMapper;
import by.it_academy.events_service.service.api.IConcertService;
import by.it_academy.events_service.service.api.IFilmService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    private final IFilmService filmService;
    private final IConcertService concertService;
    private final ConcertMapper concertMapper;
    private final FilmMapper filmMapper;

    public EventController(IFilmService filmService, IConcertService concertService,
                           ConcertMapper concertMapper, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.concertService = concertService;
        this.concertMapper = concertMapper;
        this.filmMapper = filmMapper;
    }

    @PostMapping
    public ResponseEntity<IEvent> save(@RequestBody EventDto dto) {
        IEvent responseDto;

        switch (dto.getType()) {
            case FILMS:
                responseDto = this.filmMapper.convertToFilmDto(this.filmService.save(dto));
                break;

            case CONCERTS:
                responseDto = this.concertMapper.convertToConcertDto(this.concertService.save(dto));
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + dto.getType());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{type}")
    public ResponseEntity<IMyPage<IEvent>> getAll(@PathVariable Type type,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "25") Integer size){

        PageRequest pageRequest = PageRequest.of(page, size);

        IMyPage<IEvent> eventPage;

        switch (type) {
            case FILMS:
                eventPage = this.filmMapper.convertToFilmPage(this.filmService.getAll(pageRequest));
                break;

            case CONCERTS:
                eventPage = this.concertMapper.convertToConcertPage(this.concertService.getAll(pageRequest));
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + type);
        }

        return ResponseEntity.ok(eventPage);

    }

    @GetMapping("/{type}/{uuid}")
    public ResponseEntity<IEvent> get(@PathVariable Type type,
                                           @PathVariable UUID uuid) {

        IEvent event;

        switch (type) {
            case FILMS:
                event = this.filmMapper.convertToFilmDto(this.filmService.get(uuid));
                break;

            case CONCERTS:
                event = this.concertMapper.convertToConcertDto(this.concertService.get(uuid));
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + type);
        }

        return ResponseEntity.ok(event);

    }

    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<IEvent> update(@RequestBody EventDto dto,
                       @PathVariable Type type,
                       @PathVariable UUID uuid,
                       @PathVariable("dt_update") Long dtUpdate) {

        LocalDateTime update = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());

        IEvent responseDto;

        switch (type) {
            case FILMS:
                responseDto = this.filmMapper.convertToFilmDto(this.filmService.update(dto, uuid, update));
                break;

            case CONCERTS:
                responseDto = this.concertMapper.convertToConcertDto(this.concertService.update(dto, uuid, update));
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + dto.getType());
        }

        return ResponseEntity.ok(responseDto);
    }
}
