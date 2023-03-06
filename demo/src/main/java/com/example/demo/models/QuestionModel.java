package com.example.demo.models;

import java.util.List;

public class QuestionModel {

    public QuestionModel () {

    }
    public QuestionModel(String id, String description, List<String> options, String correctAnswer) {
        this.id = id;
        this.description = description;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    private String id;
    private String description;
    private List<String> options;
    private String correctAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
