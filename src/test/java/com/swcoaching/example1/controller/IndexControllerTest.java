package com.swcoaching.example1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadMainPage() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);
        System.out.println(">>> BODY " + body);
        //then
        assertThat(body).contains("Web Service");
    }
}
