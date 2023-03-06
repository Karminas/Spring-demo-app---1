package com.example.demo.models;

public class helloWorldBeanModel {

    //Attributes
    private String message;
    private String jsonProof = "This is the second JSON parameter as proof";

    //Constructor
    public helloWorldBeanModel(String message) {
        this.message = message;
    }

    //Getters - Setters
    public String getMessage() {
        return message;
    }

    public String getJsonProof() {
        return jsonProof;
    }
    //ToString

    @Override
    public String toString() {
        return "helloWorldBeanModel{" +
                "message='" + message + '\'' +
                ", jsonProof='" + jsonProof + '\'' +
                '}';
    }
}
