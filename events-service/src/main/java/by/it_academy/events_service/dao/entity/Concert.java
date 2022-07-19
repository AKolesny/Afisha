package by.it_academy.events_service.dao.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "event", name = "events")
@SecondaryTable(schema = "event", name = "concerts", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid"))
public class Concert extends Event{

    private UUID category;

    public Concert() {
    }

    @Column(table = "concerts")
    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }
}
