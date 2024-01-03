package com.gestionProjets.entities;

import java.io.Serializable;
import java.util.Collection;
import java.time.LocalDate;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;;

@Entity
@Table (name="PROJETS")
public class Project implements Serializable {
	private String code;
	private String description;
	private LocalDate startDate;
	private Collection<Task> tasks;
	
	@Id
	@Column (name="PROJECT_CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column (name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column (name="START_DATE")
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public Project() {
		super();
	}
	public Project(String code, String description, LocalDate startDate) {
		super();
		this.code = code;
		this.description = description;
		this.startDate = startDate;
	}
	@OneToMany(mappedBy="project", cascade= {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true)
	public Collection<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return "Project [code=" + code + ", description=" + description + ", startDate=" + startDate + "]";
	}
	
}
