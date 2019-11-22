package com.example.letsplay;

public class modell { //data modelling

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public modell(String date, String time, String score) {
        this.date = date;
        this.time = time;
        this.score = score;
    }

    private String date;
    private String time;
    private String score;
    public modell()
    {

    }

//aim: map the table structure with java class

}
