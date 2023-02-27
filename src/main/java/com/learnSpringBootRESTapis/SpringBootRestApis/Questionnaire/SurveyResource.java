package com.learnSpringBootRESTapis.SpringBootRestApis.Questionnaire;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping("/surveys")
    public List<Survey> retrieveAllData() {
        return surveyService.retrieveAllData();
    }

    @RequestMapping("/surveys/{surveyId}")
    public Optional<Survey> retrieveSurveyById(@PathVariable String surveyId) {
        Optional<Survey> survey = surveyService.retrieveSurveyById(surveyId);
        if (survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return survey;
    }

    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveAllQuestions(@PathVariable String surveyId) {
        List<Question> questions = surveyService.retrieveAllQuestions(surveyId);
        if (questions == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return questions;
    }

    @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveQuestionById(@PathVariable String surveyId, @PathVariable String questionId) {
        Question question = surveyService.retrieveQuestionById(surveyId, questionId);
        if (question == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return question;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public void addNewQuestion(@PathVariable String surveyId, @RequestBody Question question) {
        surveyService.addNewQuestion(surveyId, question);
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteQuestionById(@PathVariable String surveyId, @PathVariable String questionId) {
        String deletedQuestionId = surveyService.deleteQuestionById(surveyId, questionId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateQuestionById(@PathVariable String surveyId, @PathVariable String questionId, @RequestBody Question question) {
        surveyService.updateQuestionById(surveyId, questionId, question);
        return ResponseEntity.noContent().build();
    }

}
