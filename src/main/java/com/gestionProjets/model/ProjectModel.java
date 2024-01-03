package com.gestionProjets.model;

import java.util.ArrayList;
import java.util.List;

import com.gestionProjets.entities.*;


public class ProjectModel {
	private Project project = new Project();	
	private List<Project> projects = new ArrayList<>();
	private List<Task> tasks = new ArrayList<>();
	private Task task = new Task();
	private String errors;
	private String mode;
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
}
