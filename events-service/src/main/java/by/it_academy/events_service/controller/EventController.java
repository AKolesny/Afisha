package by.it_academy.events_service.controller;

import by.it_academy.events_service.dao.entity.Event;
import by.it_academy.events_service.dto.EventDto;
import by.it_academy.events_service.dto.EventDtoOut;
import by.it_academy.events_service.dto.MyPage;
import by.it_academy.events_service.dto.enums.Type;
import by.it_academy.events_service.service.api.IConcertService;
import by.it_academy.events_service.service.api.IFilmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/afisha/event")
public class EventController {

    private final IFilmService filmService;
    private final IConcertService concertService;

    public EventController(IFilmService filmService, IConcertService concertService) {
        this.filmService = filmService;
        this.concertService = concertService;
    }

    @PostMapping
    public void save(@RequestBody EventDto dto) {
        switch (dto.getType()) {
            case FILMS:
                this.filmService.save(dto);
                break;

            case CONCERTS:
                this.concertService.save(dto);
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + dto.getType());
        }
    }

    @GetMapping("/{type}")
    public ResponseEntity<MyPage<Event>> getAll(@PathVariable Type type,
                                                @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "25") Integer size){

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Event> eventPage;

        switch (type) {
            case FILMS:
                eventPage = this.filmService.getAll(pageRequest);
                break;

            case CONCERTS:
                eventPage = this.concertService.getAll(pageRequest);
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + type);
        }

        return ResponseEntity.ok(new MyPage<>(eventPage.getNumber(), eventPage.getSize(),
                eventPage.getTotalPages(), eventPage.getTotalElements(), eventPage.isFirst(),
                eventPage.getNumberOfElements(), eventPage.isLast(), eventPage.getContent()));

    }

    @GetMapping("/{type}/{uuid}")
    public ResponseEntity<EventDtoOut> get(@PathVariable Type type,
                                           @PathVariable UUID uuid) {

        Event event;

        switch (type) {
            case FILMS:
                event = this.filmService.get(uuid);
                break;

            case CONCERTS:
                event = this.concertService.get(uuid);
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + type);
        }

        EventDtoOut dtoOut = new EventDtoOut();
        dtoOut.setUuid(event.getUuid());
        dtoOut.setDtCreate(event.getDtCreate());
        dtoOut.setDtUpdate(event.getDtUpdate());
        dtoOut.setDtEvent(event.getDtEvent());
        dtoOut.setDtEndOfSale(event.getDtEndOfSale());
        dtoOut.setDescription(event.getDescription());
        dtoOut.setTitle(event.getTitle());
        dtoOut.setStatus(event.getStatus());
        dtoOut.setType(event.getType());

        return ResponseEntity.ok(dtoOut);

    }

    @PutMapping("/{type}/{uuid}/dt_update/{dt_update}")
    public void update(@RequestBody EventDto dto,
                       @PathVariable Type type,
                       @PathVariable UUID uuid,
                       @PathVariable("dt_update") Long dtUpdate) {

        switch (type) {
            case FILMS:
                this.filmService.update(dto, uuid, dtUpdate);
                break;

            case CONCERTS:
                this.concertService.update(dto, uuid, dtUpdate);
                break;

            default:
                throw new IllegalArgumentException("Нет такого типа" + dto.getType());
        }
    }
}
