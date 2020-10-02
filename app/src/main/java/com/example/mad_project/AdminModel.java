package com.example.mad_project;

public class AdminModel {
    private int id;
    private String lname;
    private String lcontent;

    //constructors

    public AdminModel(int id, String lname, String lcontent) {
        this.id = id;
        this.lname = lname;
        this.lcontent = lcontent;
    }

    public AdminModel() {
    }

    //toString is necessary for printing the content of a class object

    @Override
    public String toString() {
        return "" +
                "\n"+
                lname +
                "\n" +
                "\n" +
                lcontent +
                "\n";
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLcontent() {
        return lcontent;
    }

    public void setLcontent(String lcontent) {
        this.lcontent = lcontent;
    }
}
