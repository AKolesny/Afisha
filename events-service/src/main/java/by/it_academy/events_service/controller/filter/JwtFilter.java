package by.it_academy.events_service.controller.filter;


import by.it_academy.events_service.controller.utils.JwtTokenUtil;
import by.it_academy.events_service.dto.security.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String userService = "http://localhost:8082/api/v1/users/me";

    private final RestTemplate template;
    private final ObjectMapper mapper;

    public JwtFilter(RestTemplateBuilder template, ObjectMapper mapper) {
        this.template = template.build();
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        ClientHttpRequest httpRequest = template.getRequestFactory()
                .createRequest(URI.create(this.userService), HttpMethod.GET);

        httpRequest.getHeaders().put(HttpHeaders.AUTHORIZATION, List.of(header));

        UserDto userDetails;

        try(ClientHttpResponse execute = httpRequest.execute()) {
            if (!execute.getStatusCode().is2xxSuccessful()) {
                throw new SecurityException("Нет такого пользователья!");
            }

            userDetails = mapper.readValue(execute.getBody(), UserDto.class);
        }


        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}