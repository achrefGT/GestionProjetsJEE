package com.gestionProjets.entities;

import java.io.Serializable;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name="TASKS")
public class Task implements Serializable {
	private String code;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private Project project;
	

	
	@Id
	@Column(name="TASK_CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "START_DATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "END_DATE")
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

	public Task() {
		super();
	}
	public Task(String code, String description, LocalDate startDate, LocalDate endDate) {
		super();
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;

	}
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name="PROJECTFK")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Override
	public String toString() {
		return "Task [ code=" + code + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + " ]";
	}
	
}
