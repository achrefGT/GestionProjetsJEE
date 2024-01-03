package com.gestionProjets.metier;

import java.util.List;


import com.gestionProjets.entities.*;

import jakarta.ejb.Local;

@Local
public interface TaskDAO {
	
	// CRUD methods
	public void addTask(Task t);
	public void deleteTask(String code);
	Task getTask(String code);
	public void updateTask(Task t);

	public List<Task> listTasks();

}
