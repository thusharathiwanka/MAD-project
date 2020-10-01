package com.example.mad_project;

public class Question {
    private int id;
    private String email,module,question;

    public Question(){

    }

    public Question(int id, String email, String module, String question) {
        this.id = id;
        this.email = email;
        this.module = module;
        this.question = question;
    }

    public Question(String email, String module, String question) {
        this.email = email;
        this.module = module;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", module='" + module + '\'' +
                ", question='" + question + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
