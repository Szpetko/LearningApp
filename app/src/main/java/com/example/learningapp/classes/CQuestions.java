package com.example.learningapp.classes;

public class CQuestions {

    private String Question, Answer_a, Answer_b, Answer_c, Answer_d;
    private int correct_ans;

    //Constructor
    public CQuestions(String question, String answer_a, String answer_b, String answer_c, String answer_d, int correct_ans) {
        Question = question;
        Answer_a = answer_a;
        Answer_b = answer_b;
        Answer_c = answer_c;
        Answer_d = answer_d;
        this.correct_ans = correct_ans;
    }

    public CQuestions() {
    }


    //To String
    @Override
    public String toString() {
        return "CQuestions{" +
                "Question='" + Question + '\'' +
                ", Answer_a='" + Answer_a + '\'' +
                ", Answer_b='" + Answer_b + '\'' +
                ", Answer_c='" + Answer_c + '\'' +
                ", Answer_d='" + Answer_d + '\'' +
                ", correct_ans=" + correct_ans +
                '}';
    }


    //Getters and Setters
    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer_a() {
        return Answer_a;
    }

    public void setAnswer_a(String answer_a) {
        Answer_a = answer_a;
    }

    public String getAnswer_b() {
        return Answer_b;
    }

    public void setAnswer_b(String answer_b) {
        Answer_b = answer_b;
    }

    public String getAnswer_c() {
        return Answer_c;
    }

    public void setAnswer_c(String answer_c) {
        Answer_c = answer_c;
    }

    public String getAnswer_d() {
        return Answer_d;
    }

    public void setAnswer_d(String answer_d) {
        Answer_d = answer_d;
    }

    public int getCorrect_ans() {
        return correct_ans;
    }

    public void setCorrect_ans(int correct_ans) {
        this.correct_ans = correct_ans;
    }
}
