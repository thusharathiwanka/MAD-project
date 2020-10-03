package com.example.mad_project;

public class Question2 {
    private int id;
    private String email,module,question,answer;

    public Question2(int id, String email, String module, String question, String answer) {
        this.id = id;
        this.email = email;
        this.module = module;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "" +
                "\nEmail='" + email + '\'' +
                "\nModule='" + module + '\'' +
                "\nQuestion='" + question + '\'' +
                "\nAnswer='" + answer + '\'' ;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
