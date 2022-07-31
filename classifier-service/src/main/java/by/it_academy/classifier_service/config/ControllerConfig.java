package by.it_academy.classifier_service.config;

import by.it_academy.classifier_service.controller.utils.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

@Configuration
public class ControllerConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperFactoryBean mapperFactoryBean() {
        Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();

        factoryBean.setObjectMapper(new ObjectMapper());
        factoryBean.setPropertyNamingStrategy(SNAKE_CASE);
        factoryBean.setModulesToInstall(JavaTimeModule.class);

        return factoryBean;
    }
}
