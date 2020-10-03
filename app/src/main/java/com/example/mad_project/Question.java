package com.example.mad_project;

public class Question {
    private int id;
    private String email,module,question,answer;

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

    public Question(int id, String email, String module, String question, String answer) {
        this.id = id;
        this.email = email;
        this.module = module;
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "" +
                "\nId=" + id +
                "\nEmail='" + email + '\'' +
                "\nModule='" + module + '\'' +
                "\nQuestion='" + question + '\'';



    }

    /*@Override
    public String toString1() {
        return "Question{" +
                "email='" + email + '\'' +
                ", module='" + module + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }*/

   /* public String display(){
        return "" +
                "\nemail='" + email + '\'' +
                "\nmodule='" + module + '\'' +
                "\nquestion='" + question + '\''+
                "\nanswer='"+ answer +'\'';
    }*/

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
