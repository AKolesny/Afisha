package by.it_academy.classifier_service.dto.country;

import by.it_academy.classifier_service.controller.utils.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class CountryOutDto {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;
    private String description;

    public CountryOutDto() {
    }

    public UUID getUuid() {
        return uuid;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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
}
