package com.coderulez.senai.leansurvey.model;


import java.math.BigDecimal;
import java.util.Date;

import android.os.Parcel;

import com.coderulez.senai.leansurvey.model.Repository.ICallback;
import com.coderulez.senai.leansurvey.model.Repository.QuestionRepository;

/**
 * Questionnaire generated by hbm2java
 */

public class Questionnaire
{

	private Long id;
	private Employee employee;
	private Enterprise enterprise;
	private String title;
	private String description;
	private BigDecimal interviewerPayment;
	private Date startDate;
	private Date endDate;
	private int limite;

	public Questionnaire()
	{

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getInterviewerPayment() {
		return interviewerPayment;
	}

	public void setInterviewerPayment(BigDecimal interviewerPayment) {
		this.interviewerPayment = interviewerPayment;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public void getQuestions(ICallback<Question[]> cb)
	{
		QuestionRepository.List(this.id, cb);
	}
}
