package com.example.demo.models;

import java.util.List;

public class SurveyModel {
    public SurveyModel () {}

    public SurveyModel(String id, String title, String description, List<QuestionModel> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    private String id;
    private String title;
    private String description;
    private List<QuestionModel> questions;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "SurveyModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }
}
