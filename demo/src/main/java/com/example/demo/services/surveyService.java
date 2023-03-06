package com.example.demo.services;

import com.example.demo.models.QuestionModel;
import com.example.demo.models.SurveyModel;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class surveyService {
    private static List<SurveyModel> surveys = new ArrayList<>();

    static {
        QuestionModel question1 = new QuestionModel("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        QuestionModel question2 = new QuestionModel("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        QuestionModel question3 = new QuestionModel("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<QuestionModel> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        SurveyModel survey = new SurveyModel("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    //------------------------------------------------------------------------------------------------------
    //Survey services-------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    public List<SurveyModel> retrieveAllSurveys () {
        return surveys;
    }

    public SurveyModel findSurveyById (String id) {
        Predicate<? super SurveyModel> predicate = surveyModel -> surveyModel.getId().equalsIgnoreCase(id);
        return surveys.stream().filter(predicate).findFirst().orElse(null);
    }

    //------------------------------------------------------------------------------------------------------
    //Questions services-------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    public List<QuestionModel> retrieveAllQuestions (String surveyId) {
        SurveyModel foundSurvey = findSurveyById(surveyId);
        if (foundSurvey == null) {return null;}
        return foundSurvey.getQuestions();
    }
    public QuestionModel findQuestionById (String surveyId, String questionId) {
        //Search for target survey
        List<QuestionModel> questions = retrieveAllQuestions(surveyId);
        if (surveyId == null) {return null;}

        //predicate for question filter in target survey
        Predicate<? super QuestionModel> predicate = questionModel -> questionModel.getId().equalsIgnoreCase(questionId);
        return questions.stream().filter(predicate).findFirst().orElse(null);
    }

    public String addNewSurveyQuestion (String surveyId, QuestionModel question) {
        List<QuestionModel> questions = retrieveAllQuestions(surveyId);

        question.setId(generateNewRandomId());
        questions.add(question);
        return question.getId();
    }

    public String deleteSurveyQuestion (String surveyId, String questionId) {
        List<QuestionModel> questions = retrieveAllQuestions(surveyId);
        Predicate<? super QuestionModel> predicate = questionModel -> questionModel.getId().equalsIgnoreCase(questionId);
        if (questions.removeIf(predicate)){
            return questionId;
        }
        else{return null;}
    }

    public String updateSurveyQuestion (String surveyId, String questionId, QuestionModel question) {
        List<QuestionModel> questions = retrieveAllQuestions(surveyId);
        Predicate<? super QuestionModel> predicate = questionModel -> questionModel.getId().equalsIgnoreCase(questionId);
        if (questions.removeIf(predicate)){
            questions.add(question);
            return question.getId();
        }
        else{
            return null;
        }
    }

    //------------------------------------------------------------------------------------------------------
    //General services-------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    private static String generateNewRandomId() {
        //Create a secure random ID
        SecureRandom secureRandom = new SecureRandom();
        String newlyGeneratedRandomId = new BigInteger(32, secureRandom).toString();
        return newlyGeneratedRandomId;
    }
}
