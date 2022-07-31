package by.it_academy.events_service.mappers;

import by.it_academy.events_service.dao.entity.Concert;
import by.it_academy.events_service.dto.api.IEvent;
import by.it_academy.events_service.dto.api.IMyPage;
import by.it_academy.events_service.dto.event.ConcertOutDto;
import by.it_academy.events_service.dto.event.EventDto;
import by.it_academy.events_service.dto.page.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ConcertMapper {

    public ConcertOutDto convertToConcertDto(Concert concert) {
        ConcertOutDto dto = new ConcertOutDto();

        dto.setUuid(concert.getUuid());
        dto.setDtCreate(concert.getDtCreate());
        dto.setDtUpdate(concert.getDtUpdate());
        dto.setTitle(concert.getTitle());
        dto.setDescription(concert.getDescription());
        dto.setDtEvent(concert.getDtEvent());
        dto.setDtEndOfSale(concert.getDtEndOfSale());
        dto.setType(concert.getType());
        dto.setStatus(concert.getStatus());
        dto.setAuthor(concert.getAuthor());

        return dto;
    }


    public IMyPage<IEvent> convertToConcertPage(Page<Concert> page) {
        MyPage<IEvent> myPage = new MyPage<>();

        myPage.setNumber(page.getNumber());
        myPage.setSize(page.getSize());
        myPage.setTotalPages(page.getTotalPages());
        myPage.setTotalElements(page.getTotalElements());
        myPage.setFirst(page.isFirst());
        myPage.setNumberOfElements(page.getNumberOfElements());
        myPage.setLast(page.isLast());

        List<IEvent> content = new ArrayList<>();
        for (Concert concert : page.getContent()) {
            content.add(convertToConcertDto(concert));
        }

        myPage.setContent(content);

        return myPage;
    }

    public Concert convertToConcert(EventDto dto) {
        Concert concert = new Concert();
        concert.setUuid(UUID.randomUUID());
        concert.setDtCreate(LocalDateTime.now());
        concert.setDtUpdate(concert.getDtUpdate());
        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setStatus(dto.getStatus());
        concert.setCategory(dto.getCountry());

        return concert;
    }

    public void updateConcert(EventDto dto, Concert concert) {

        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setType(dto.getType());
        concert.setStatus(dto.getStatus());
        concert.setCategory(dto.getCountry());
    }




}
