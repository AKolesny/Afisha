package by.it_academy.events_service.service;

import by.it_academy.events_service.service.api.IClassifierService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Service
public class ClassifierService implements IClassifierService {

    private final String country = "http://localhost:8080/api/v1/classifier/country/";
    private final String category = "http://localhost:8080/api/v1/classifier/concert/category/";

    private final RestTemplate template;

    public ClassifierService(RestTemplateBuilder templateBuilder) {
        this.template = templateBuilder.build();
    }

    @Override
    public void isUuid(String url, UUID uuid) throws IllegalArgumentException {
        HttpStatus status;
        ClientHttpRequest request;

        try {
            request = template.getRequestFactory()
                    .createRequest(URI.create(url + uuid), HttpMethod.GET);

            try (ClientHttpResponse response = request.execute()) {
                status = response.getStatusCode();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!status.is2xxSuccessful()) {
            throw new IllegalArgumentException("В справочнике отсутствует uuid");
        }
    }

    @Override
    public void isCountry(UUID uuid) throws IllegalArgumentException {
        isUuid(this.country, uuid);
    }

    @Override
    public void isCategory(UUID uuid) throws IllegalArgumentException {
        isUuid(this.category, uuid);
    }
}
