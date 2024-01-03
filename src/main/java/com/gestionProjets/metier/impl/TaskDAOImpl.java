package com.gestionProjets.metier.impl;

import java.util.List;





import com.gestionProjets.entities.*;
import com.gestionProjets.metier.TaskDAO;
import com.gestionProjets.metier.ProjectDAO;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name="taskBean")
public class TaskDAOImpl implements TaskDAO {

	@PersistenceContext (unitName = "managementUnit")
	EntityManager em;
	
	@EJB
	ProjectDAO projectDAO;
	
	@Override
	public void addTask(Task t) {
		em.persist(t);
		
	}

	@Override
	public List<Task> listTasks() {
		
		String requeteJPQL= "select t from Task t "; 
		Query query= em.createQuery(requeteJPQL);
		
				List<Task>  Tasks=  (List<Task>)query.getResultList();

		return Tasks;
		
	}

	@Override
	public Task getTask(String code) {
		
		Task t= em.find(Task.class, code);
		return t;
	}

	@Override
	public void updateTask(Task t) {
		em.merge(t);
		
	}

	@Override
	public void deleteTask(String code) {
		Task t= getTask(code);
		em.remove(t);
		
	}
	
}


