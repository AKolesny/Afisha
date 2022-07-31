package by.it_academy.classifier_service.controller.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            jsonGenerator.writeNumber(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        } else {
            jsonGenerator.writeNull();
        }
    }
}
