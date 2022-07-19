package by.it_academy.events_service.dao.entity;

import by.it_academy.events_service.dto.enums.Status;
import by.it_academy.events_service.dto.enums.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class Event {
   private UUID uuid;
   private LocalDateTime dtCreate;
   private LocalDateTime dtUpdate;
   private String title;
   private String description;
   private LocalDateTime dtEvent;
   private LocalDateTime dtEndOfSale;
   private Type type;
   private Status status;

    public Event() {
    }

    @Id
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "dt_create")
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Version
    @Column(name = "dt_update")
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
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

    @Column(name = "dt_event")
    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    @Column(name = "dt_end_of_sale")
    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public void setDtEndOfSale(LocalDateTime dtEndOfSale) {
        this.dtEndOfSale = dtEndOfSale;
    }

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
