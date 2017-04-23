package com.example.samsung.quizclash.api;

import java.util.List;
import java.util.Vector;
import java.io.Serializable;

public class Question implements Serializable{
    private static final long serialVersionUID = 5641855209374922614L;
    private int id;
    private String text;
    private List<Answer> answers = new Vector<>();

    public Question() {
        super();
    }


    public Question(int id, String text) {
        super();
        this.id = id;
        this.text = text;
    }

    public Question(int id, String text, Vector<Answer> answers) {
        super();
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public void addAnswer(int id, String text) {
        answers.add(new Answer(id, text));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", text=" + text +  "Answers =" + answers + "]";
    }

}
