package com.github.tadaskay.puzzle15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.CREATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PuzzleResourceRestTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private Generator generator;

    @Test
    public void solve2x2Puzzle() {
        // given
        when(generator.generateTiles()).thenReturn(new Integer[][]{
            new Integer[]{0, 1},
            new Integer[]{3, 2},
        });

        // when
        ResponseEntity<PuzzleRepresentation> res = restTemplate.postForEntity("/api/puzzles", null, PuzzleRepresentation.class);
        PuzzleRepresentation puzzle = res.getBody();
        // then
        assertThat(res.getHeaders().getLocation(), notNullValue());
        assertThat(res.getStatusCode(), is(CREATED));
        assertThat(puzzle.isComplete(), is(false));
        assertThat(puzzle.getTiles().size(), is(2));
        assertThat(puzzle.getTiles().get(0), hasItems(0, 1));
        assertThat(puzzle.getTiles().get(1), hasItems(3, 2));
        assertThat(puzzle.hasLink("move-up"), is(true));
        assertThat(puzzle.hasLink("move-right"), is(false));
        assertThat(puzzle.hasLink("move-down"), is(false));
        assertThat(puzzle.hasLink("move-left"), is(true));

        // when
        String moveLeftUri = puzzle.getLink("move-left").getHref();
        res = restTemplate.exchange(moveLeftUri, PUT, null, new ParameterizedTypeReference<PuzzleRepresentation>() {
        });
        puzzle = res.getBody();
        // then
        assertThat(res.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(puzzle.isComplete(), is(false));
        assertThat(puzzle.getTiles().get(0), hasItems(1, 0));
        assertThat(puzzle.getTiles().get(1), hasItems(3, 2));
        assertThat(puzzle.hasLink("move-up"), is(true));
        assertThat(puzzle.hasLink("move-right"), is(true));
        assertThat(puzzle.hasLink("move-down"), is(false));
        assertThat(puzzle.hasLink("move-left"), is(false));

        // when
        String moveUpUri = puzzle.getLink("move-up").getHref();
        res = restTemplate.exchange(moveUpUri, PUT, null, new ParameterizedTypeReference<PuzzleRepresentation>() {
        });
        puzzle = res.getBody();
        // then
        assertThat(res.getStatusCode().is2xxSuccessful(), is(true));
        assertThat(puzzle.getTiles().get(0), hasItems(1, 2));
        assertThat(puzzle.getTiles().get(1), hasItems(3, 0));
        assertThat(puzzle.isComplete(), is(true));
    }
}
