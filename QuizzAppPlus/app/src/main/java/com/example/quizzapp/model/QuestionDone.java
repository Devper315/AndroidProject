package com.example.quizzapp.model;

import com.example.quizzapp.ui.quizz.QuizzAdapter;

import java.io.Serializable;

public class QuestionDone implements Serializable {
    private int id;
    private String option1, option2, option3, option4, question, answer, selected;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private Result result;

    public QuestionDone() {

    }

    public QuestionDone(int id, String option1, String option2, String option3, String option4, String question, String answer, String selected) {
        this.id = id;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
        this.answer = answer;
        this.selected = selected;
    }

    public QuestionDone (QuizzAdapter.QuizzViewHolder viewHolder){
        this.option1 = viewHolder.option1.getText().toString();
        this.option2 = viewHolder.option2.getText().toString();
        this.option3 = viewHolder.option3.getText().toString();
        this.option4 = viewHolder.option4.getText().toString();
        this.answer = viewHolder.answer.getText().toString();
        this.question = viewHolder.answer.getText().toString();
        if (viewHolder.option1.isChecked()) this.selected = option1;
        if (viewHolder.option2.isChecked()) this.selected = option2;
        if (viewHolder.option3.isChecked()) this.selected = option3;
        if (viewHolder.option4.isChecked()) this.selected = option4;
    }

    public boolean markQuestion(){
        return this.selected.equals(this.answer);
    }
    public int getId() {
        return id;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
