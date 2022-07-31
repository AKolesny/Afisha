package by.it_academy.events_service.dto.event;

import by.it_academy.events_service.dao.enums.Status;
import by.it_academy.events_service.dao.enums.Type;
import by.it_academy.events_service.dto.api.IEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class FilmOutDto implements IEvent {
    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private Type type;
    private Status status;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;
    private String author;

    public FilmOutDto() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getAuthor() {
        return author;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
