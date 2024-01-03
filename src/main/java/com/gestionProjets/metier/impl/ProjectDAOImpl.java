package com.gestionProjets.metier.impl;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;





import com.gestionProjets.entities.*;
import com.gestionProjets.metier.ProjectDAO;
import com.gestionProjets.metier.TaskDAO;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name="projectBean")
public class ProjectDAOImpl implements ProjectDAO {

	@PersistenceContext (unitName = "managementUnit")
	EntityManager em;
	
	@EJB
	TaskDAO taskDAO;
	
	
	@Override
	public void addProject(Project p) {
		em.persist(p);
		
	}

	@Override
	public List<Project> listProjects() {
		
		String requeteJPQL= "select p from Project p "; 
		Query query= em.createQuery(requeteJPQL);
		
				List<Project>  projects=  (List<Project>)query.getResultList();

		return projects;
		
	}

	@Override
	public Project getProject(String code) {
		
		Project p= em.find(Project.class, code);
		return p;
	}

	@Override
	public void updateProject(Project p) {
		em.merge(p);
		
	}

	@Override
	public void deleteProject(String code) {
		Project p= getProject(code);
		em.remove(p);
		
	}
	@Override
	public void addTaskToProject(Task t) {
	    Project p = t.getProject();
	    taskDAO.addTask(t);

	    if (p != null) {
	        Collection<Task> tasks = p.getTasks();
	        if (tasks == null) {
	            tasks = new ArrayList<>();
	        }
	        tasks.add(t);
	        p.setTasks(tasks);
	        em.merge(p);
	    }
	}

	@Override
	public void removeTaskFromProject(Task t) {
	    Project p = t.getProject();

	    if (p != null) {
	        List<Task> tasks = (List<Task>) p.getTasks();
	        if (tasks != null) {
	            tasks.remove(t);
	            taskDAO.deleteTask(t.getCode());
	            p.setTasks(tasks);
	            em.merge(p);
	        }
	    }
	}

}


