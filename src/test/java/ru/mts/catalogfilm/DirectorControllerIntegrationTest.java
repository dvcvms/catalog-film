package ru.mts.catalogfilm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.catalogfilm.entity.DirectorEntity;
import ru.mts.catalogfilm.entity.FilmEntity;
import ru.mts.catalogfilm.repository.DirectorRepository;
import ru.mts.catalogfilm.service.DirectorService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DirectorControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private DirectorService directorService;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testAddNewDirectorWithJSON() {
        DirectorEntity expectedDirector = new DirectorEntity();
        expectedDirector.setFilmList(List.of(
                createFilm("Rocky", 7),
                createFilm("Terminator", 8)));

        HttpEntity<String> entity = new HttpEntity<>(asJsonString(expectedDirector), headers);
        ResponseEntity<DirectorEntity> response =
                restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, DirectorEntity.class);
        DirectorEntity actualDirector = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualDirector);
        assertNotNull(directorRepository.findById(actualDirector.getUuid()));
        assertNotNull(directorService.getDirector(actualDirector.getUuid()));
        assertThat(actualDirector.getFilmList().size()).isEqualTo(expectedDirector.getFilmList().size());
    }

    @Test
    public void testFindDirectorByDirectorUuid() {
        DirectorEntity expectedDirector = new DirectorEntity();
        expectedDirector.setFilmList(List.of(
                createFilm("Alex", 25),
                createFilm("Elena", 22)));
        directorRepository.save(expectedDirector);

        ResponseEntity<DirectorEntity> response =
                restTemplate.getForEntity(createURLWithPort() + "?directorUuid={directorUuid}", DirectorEntity.class, expectedDirector.getUuid());
        DirectorEntity actualDirector = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualDirector).isEqualTo(expectedDirector);
    }

    private FilmEntity createFilm(String title, int rating) {
        FilmEntity film = new FilmEntity();
        film.setTitle(title);
        film.setRating(rating);
        return film;
    }

    private String createURLWithPort() {
        return String.format("http://localhost:%d/director", port);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
