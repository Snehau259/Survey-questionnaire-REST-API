package com.learnSpringBootRESTapis.SpringBootRestApis.Questionnaire;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyTest {

    private String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
    @Autowired
    private TestRestTemplate template;

    @Test
    public void questionResponseTest() throws JSONException {
        String expectedResponse = """
                {"id":"Question1","question":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
                """;
        ResponseEntity<String> responseEntity = template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
        JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(),false);

    }
}
