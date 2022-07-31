package by.it_academy.classifier_service.mappers;

import by.it_academy.classifier_service.dao.entity.Country;
import by.it_academy.classifier_service.dto.api.IMyPage;
import by.it_academy.classifier_service.dto.country.CountryDto;
import by.it_academy.classifier_service.dto.country.CountryOutDto;
import by.it_academy.classifier_service.dto.page.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CountryMapper {

    public CountryOutDto convertToDto(Country country) {
        CountryOutDto dto = new CountryOutDto();

        dto.setUuid(country.getUuid());
        dto.setDtCreate(country.getDtCreate());
        dto.setDtUpdate(country.getDtUpdate());
        dto.setTitle(country.getTitle());
        dto.setDescription(country.getDescription());

        return dto;
    }

    public Country convertToCountry(CountryDto dto) {
        Country country = new Country();

        country.setUuid(UUID.randomUUID());
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(country.getDtUpdate());
        country.setTitle(dto.getTitle());
        country.setDescription(country.getDescription());

        return country;
    }

    public IMyPage<CountryOutDto> convertToMyPage(Page<Country> page) {
        MyPage<CountryOutDto> myPage = new MyPage<>();

        myPage.setNumber(page.getNumber());
        myPage.setSize(page.getSize());
        myPage.setTotalPages(page.getTotalPages());
        myPage.setTotalElements(page.getTotalElements());
        myPage.setFirst(page.isFirst());
        myPage.setNumberOfElements(page.getNumberOfElements());
        myPage.setLast(page.isLast());

        List<CountryOutDto> content = new ArrayList<>();
        for (Country country : page.getContent()) {
            content.add(convertToDto(country));
        }

        myPage.setContent(content);

        return myPage;
    }

}
