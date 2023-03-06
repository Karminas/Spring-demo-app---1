package com.example.demo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class surveyResource {

    String uriResponse = """
                {
                    "id": "Question1",
                    "description": "Most Popular Cloud Platform Today",
                    "options": [
                        "AWS",
                        "Azure",
                        "Google Cloud",
                        "Oracle Cloud"
                    ],
                    "correctAnswer": "AWS"
                }
                """;

    private static String uri = "/surveys/survey1/questions/question1";
    @Autowired
    TestRestTemplate template;

    @Test
    void retrieveSpecificSurveyQuestion_basicScenario(){
        ResponseEntity<String> responseEntity = template.getForEntity(uri, String.class);
        String expectedResponse = """
                {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());

        assertEquals(expectedResponse.trim(), responseEntity.getBody());

    }

    @Test
    void retrieveSpecificSurveyQuestion_JSONAssertsScenario () throws JSONException {
        ResponseEntity<String> responseEntity = template.getForEntity(uri, String.class);
        String expectedResponse = """
                {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;
        JSONAssert.assertEquals(expectedResponse,responseEntity.getBody(), true);

    }
}
