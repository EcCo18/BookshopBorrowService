package com.example.borrowservice.services;


import com.example.borrowservice.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    public boolean checkIfUserExists(int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            String requestUrl = String.format("http://%s:%s/api/user/%d",
                    System.getenv("USER_APP_SERVICE_SERVICE_HOST"),
                    System.getenv("USER_APP_SERVICE_SERVICE_PORT"),
                    userId);
            log.info(requestUrl);
            ResponseEntity<User> responseEntity =
                    restTemplate.exchange(requestUrl, HttpMethod.GET, entity, User.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("404")) {
                return false;
            }
            log.error("couldn't connect to userService to check if userId: " + userId + " is valid");
        }

        return false;
    }
}
