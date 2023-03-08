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
import ru.mts.catalogfilm.constans.GenreEnum;
import ru.mts.catalogfilm.entity.FilmEntity;
import ru.mts.catalogfilm.repository.FilmRepository;
import ru.mts.catalogfilm.service.FilmService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FilmControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testAddNewFilmWithJSON() {
        FilmEntity expectedFilm = new FilmEntity();
        expectedFilm.setTitle("Lord of the Rings");
        expectedFilm.setGenre(GenreEnum.ACTION);
        expectedFilm.setRating(9);

        HttpEntity<String> entity = new HttpEntity<>(asJsonString(expectedFilm), headers);
        ResponseEntity<FilmEntity> response =
                restTemplate.exchange(createURLWithPort(), HttpMethod.POST, entity, FilmEntity.class);
        FilmEntity actualFilm = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualFilm);
        assertNotNull(filmRepository.findById(actualFilm.getUuid()));
        assertNotNull(filmService.getFilm(actualFilm.getUuid()));
        assertThat(actualFilm.getTitle()).isEqualTo(expectedFilm.getTitle());
        assertThat(actualFilm.getGenre()).isEqualTo(expectedFilm.getGenre());
        assertThat(actualFilm.getRating()).isEqualTo(expectedFilm.getRating());
    }

    @Test
    public void testFindFilmByFilmUuid() {
        FilmEntity expectedFilm = new FilmEntity();
        expectedFilm.setTitle("Saw IV");
        expectedFilm.setGenre(GenreEnum.HORROR);
        expectedFilm.setRating(8);
        filmRepository.save(expectedFilm);

        ResponseEntity<FilmEntity> response =
                restTemplate.getForEntity(createURLWithPort() + "?filmUuid={filmUuid}", FilmEntity.class, expectedFilm.getUuid());
        FilmEntity actualFilm = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualFilm).isEqualTo(expectedFilm);
    }

    private String createURLWithPort() {
        return String.format("http://localhost:%d/film", port);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
