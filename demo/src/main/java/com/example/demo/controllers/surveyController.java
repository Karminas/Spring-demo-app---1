package com.example.demo.controllers;

import com.example.demo.models.QuestionModel;
import com.example.demo.models.SurveyModel;
import com.example.demo.services.surveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class surveyController {

    private surveyService surveyService;

    public surveyController(com.example.demo.services.surveyService surveyService) {
        this.surveyService = surveyService;
    }

    //------------------------------------------------------------------------------------------------------
    //Surveys endpoints-------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    @RequestMapping(value="/surveys")
    public List<SurveyModel> retrieveAllSurveys () {
        return surveyService.retrieveAllSurveys();
    }
    @RequestMapping(value="/surveys/{id}")
    public SurveyModel retrieveSurveyById (@PathVariable String id) {
        SurveyModel result = surveyService.findSurveyById(id);
        if (result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------
    //Questions endpoints-------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/surveys/{id}/questions")
    public List<QuestionModel> retrieveAllQuestions(@PathVariable String id){
        List<QuestionModel> questions = surveyService.retrieveAllQuestions(id);
        if (questions.isEmpty()) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return questions;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}")
    public QuestionModel retrieveQuestionById(@PathVariable String surveyId, @PathVariable String questionId){
        QuestionModel question = surveyService.findQuestionById(surveyId, questionId);
        if (question == null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        return question;
    }

    @RequestMapping(value = "/surveys/{id}/questions", method = RequestMethod.POST)
    public ResponseEntity<Object> addQuestionToSurvey(@PathVariable String id, @RequestBody QuestionModel question){
        String questionId = surveyService.addNewSurveyQuestion(id, question);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/surveys/{id}/questions/{questionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteQuestionToSurvey(@PathVariable String id, @PathVariable String questionId){
        String targetId = surveyService.deleteSurveyQuestion(id, questionId);
        if (targetId == null) { return ResponseEntity.notFound().build();}
        else {return ResponseEntity.ok().build();}
    }

    @RequestMapping(value = "/surveys/{id}/questions/{questionId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateQuestionToSurvey(@PathVariable String id, @PathVariable String questionId, @RequestBody QuestionModel updatedModel){
        String updatedQuestionId = surveyService.updateSurveyQuestion(id, questionId, updatedModel);
        if (updatedQuestionId != null) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
