package by.it_academy.events_service.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "event", name = "events")
@SecondaryTable(schema = "event", name = "films", pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid"))
public class Film extends Event{
    private UUID country;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private Integer duration;

    public Film() {
    }

    @Column(table = "films")
    public UUID getCountry() {
        return country;
    }

    public void setCountry(UUID country) {
        this.country = country;
    }

    @Column(table = "films", name = "release_year")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(table = "films", name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(table = "films")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
