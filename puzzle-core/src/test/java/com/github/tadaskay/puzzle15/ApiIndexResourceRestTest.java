package com.github.tadaskay.puzzle15;

import com.github.tadaskay.puzzle15.lang.HalResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ApiIndexResourceRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void servesApiIndex() {
        // when
        ResponseEntity<HalResource> res = restTemplate.getForEntity("/api", HalResource.class);
        // then
        assertThat(res.getStatusCode(), is(HttpStatus.OK));
        assertThat(res.getBody().hasLink("new-puzzle"), is(true));
    }
}
