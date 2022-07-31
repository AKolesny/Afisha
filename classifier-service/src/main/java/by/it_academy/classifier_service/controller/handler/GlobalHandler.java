package by.it_academy.classifier_service.controller.handler;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandler {

    private final static String LOGREF = "logref";
    private final static String MESSAGE = "message";
    private final static String ERROR = "error";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handler(RuntimeException e) {
        return Map.of(LOGREF, ERROR,
                MESSAGE, "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handler(EntityNotFoundException e) {
        return Map.of(LOGREF, ERROR,
                MESSAGE, "uuid не существует"
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handler(HttpMessageNotReadableException e) {
        return Map.of(LOGREF, ERROR,
                MESSAGE, "Запрос не корректен!"
        );
    }
}
