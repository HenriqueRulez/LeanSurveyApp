package com.coderulez.senai.leansurvey.model;


/**
 * Questionrulegroup generated by hbm2java
 */

public class Questionrulegroup implements java.io.Serializable {

    private long id;
	private Questionrule questionrule;
	private Question question;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Questionrule getQuestionrule() {
		return questionrule;
	}

	public void setQuestionrule(Questionrule questionrule) {
		this.questionrule = questionrule;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}
