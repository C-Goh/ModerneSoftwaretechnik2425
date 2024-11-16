package com.softwaretechnik.spielekiste.question.domain.entity;

public class QuestionEntity {
    
    private int id;

    private String frage;

    private String antwort;
    
    private String[] moeglicheAntworten;

    public QuestionEntity() {
    }

    public QuestionEntity(int id, String frage, String antwort, String[] moeglicheAntworten) {
        this.id = id;
        this.frage = frage;
        this.antwort = antwort;
        this.moeglicheAntworten = moeglicheAntworten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    public String[] getMoeglicheAntworten() {
        return moeglicheAntworten;
    }

    public void setMoeglicheAntworten(String[] moeglicheAntworten) {
        this.moeglicheAntworten = moeglicheAntworten;
    }
}