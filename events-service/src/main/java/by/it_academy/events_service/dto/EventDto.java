package by.it_academy.events_service.dto;

import by.it_academy.events_service.dto.enums.Status;
import by.it_academy.events_service.dto.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventDto {
    private String title;
    private String description;
    private LocalDateTime dtEvent;
    private LocalDateTime dtEndOfSale;
    private Type type;
    private Status status;
    private UUID country;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public EventDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }


    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MM yyyy")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
