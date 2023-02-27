package com.learnSpringBootRESTapis.SpringBootRestApis.Questionnaire;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllData() {
        return surveys;
    }

    public Optional<Survey> retrieveSurveyById(String surveyId) {
        Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyId);
        Optional<Survey> survey = surveys.stream().filter(predicate).findFirst();
        if (survey.isEmpty())
            return null;
        return survey;
    }

    public List<Question> retrieveAllQuestions(String surveyId) {

        Optional<Survey> survey = retrieveSurveyById(surveyId);
        if (survey.isEmpty())
            return null;
        return survey.get().getQuestions();
    }

    public Question retrieveQuestionById(String surveyId, String questionId) {
        List<Question> questions = retrieveAllQuestions(surveyId);
        if (questions == null)
            return null;
        Predicate<? super Question> predicate = question -> question.getId().equalsIgnoreCase(questionId);
        Optional<Question> question = questions.stream().filter(predicate).findFirst();
        if (question.isEmpty())
            return null;
        return question.get();

    }

    public void addNewQuestion(String surveyId, Question question) {
        List<Question> questions = retrieveAllQuestions(surveyId);
        question.setId(generateRandomId());
        questions.add(question);
    }

    private static String generateRandomId() {
        SecureRandom secureRandom = new SecureRandom();
        String randomId = new BigInteger(32, secureRandom).toString();
        return randomId;
    }
}
