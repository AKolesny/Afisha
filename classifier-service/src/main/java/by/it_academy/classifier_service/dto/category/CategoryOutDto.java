package by.it_academy.classifier_service.dto.category;

import by.it_academy.classifier_service.controller.utils.json.LocalDateTimeDeserializer;
import by.it_academy.classifier_service.controller.utils.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryOutDto {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;

    public CategoryOutDto() {
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
}
