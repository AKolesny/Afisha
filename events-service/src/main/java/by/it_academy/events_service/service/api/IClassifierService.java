package by.it_academy.events_service.service.api;

import java.util.UUID;

public interface IClassifierService {

    void isUuid(String url, UUID uuid) throws IllegalArgumentException;

    void isCountry(UUID uuid) throws IllegalArgumentException;

    void isCategory(UUID uuid) throws IllegalArgumentException;
}
