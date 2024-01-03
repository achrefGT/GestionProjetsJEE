package com.gestionProjets.metier;

import java.util.List;


import com.gestionProjets.entities.*;

import jakarta.ejb.Local;

@Local
public interface ProjectDAO {
	
	// CRUD methods
	public void addProject(Project p);
	public void deleteProject(String code);
	Project getProject(String code);
	public void updateProject(Project p);

	public void addTaskToProject(Task t);
	public void removeTaskFromProject(Task t);
	public List<Project> listProjects();

}
