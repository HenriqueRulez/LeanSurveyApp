package com.coderulez.senai.leansurvey.model;


/**
 * Answerquestion generated by hbm2java
 */

public class Answerquestion {

	private Long id;
	private Question question;
	private Questionnaireanswer questionnaireanswer;
	private Integer answerInt;
	private String answerString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Questionnaireanswer getQuestionnaireanswer() {
		return questionnaireanswer;
	}

	public void setQuestionnaireanswer(Questionnaireanswer questionnaireanswer) {
		this.questionnaireanswer = questionnaireanswer;
	}

	public Integer getAnswerInt() {
		return answerInt;
	}

	public void setAnswerInt(Integer answerInt) {
		this.answerInt = answerInt;
	}

	public String getAnswerString() {
		return answerString;
	}

	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
}