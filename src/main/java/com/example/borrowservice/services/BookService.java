package com.example.borrowservice.services;

import com.example.borrowservice.models.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final RestTemplate restTemplate;

    public boolean checkIfBookExists(int bookId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            String requestUrl = String.format("http://%s:%s/api/book/%d",
                    System.getenv("BOOK_APP_SERVICE_SERVICE_HOST"),
                    System.getenv("BOOK_APP_SERVICE_SERVICE_PORT"),
                    bookId);
            log.info(requestUrl);
            ResponseEntity<Book> responseEntity =
                    restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Book.class);

            return responseEntity.getStatusCode() == HttpStatus.OK;
        } catch (RestClientException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("404")) {
                return false;
            }
            log.error("couldn't connect to bookService for checking if bookId: " + bookId + " is valid");
        }

        return false;
    }
}
