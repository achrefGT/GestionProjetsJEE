package com.gestionProjets.controleurs;

import java.io.IOException;
import java.time.LocalDate;


import com.gestionProjets.entities.*;
import com.gestionProjets.metier.*;
import com.gestionProjets.model.TaskModel;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControleurProjectServlet
 */
@WebServlet("/ControleurTaskServlet")
public class ControleurTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
	private TaskDAO metierT;
    @EJB
    private ProjectDAO metierP;

	
	
	@Override
	public void init() throws ServletException {
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        TaskModel model = new TaskModel();
	        request.setAttribute("model", model);


	        model.setProjects(metierP.listProjects());

	        String action = request.getParameter("action");
	        String mode = request.getParameter("mode");
	        if ("update".equals(mode)) {
	        	String code = request.getParameter("taskCode");
	        	model.setMode(mode);
	        	model.setTask(metierT.getTask(code));
	        }

	        if (action != null) {
	            switch (action) {
	                case "remove":
	                    handleRemoveAction(request, response, model);
	                    return;
	                case "update":
	                    handleUpdateAction(request, response, model);
	                    return;
	                case "create":
	                    handleCreateAction(request, response, model);
	                    return;
	                case "list":
	                    handleSeeAllAction(request, response, model);
	                    return;
	                case "search":
	                    handleSearchAction(request, response, model);
	                    return;
	                default:
	                    break;
	            }
	        }

	        request.getRequestDispatcher("/WEB-INF/task/task.jsp").forward(request, response);
	    }

	    private void handleRemoveAction(HttpServletRequest request, HttpServletResponse response, TaskModel model) throws ServletException, IOException {
	        try {
	        	String code = request.getParameter("taskCode");
		        Task existingTask = metierT.getTask(code);
		        if (existingTask != null ) {
		        	model.setTask(existingTask);
			        metierP.removeTaskFromProject(existingTask);
			        request.getRequestDispatcher("/WEB-INF/task/successRemoveTask.jsp").forward(request, response);
		        }
		        else {
		            model.setErrors("Task not found for code: " + code);
		            request.getRequestDispatcher("/WEB-INF/task/failedRemoveTask.jsp").forward(request, response);
		        }
	        } catch (Exception e) {
	        	model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/task/failedRemoveTask.jsp").forward(request, response);
			}
	    	
	    }

	    private void handleUpdateAction(HttpServletRequest request, HttpServletResponse response, TaskModel model)
	            throws ServletException, IOException {
	        try {
	            String taskCode = request.getParameter("taskCode");
	            Task existingTask = metierT.getTask(taskCode);

	            if (existingTask != null) {
	                String taskDescription = request.getParameter("taskDescription");
	                LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
	                LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
	                int result1 = startDate.compareTo(existingTask.getProject().getStartDate());
		            int result2 = endDate.compareTo(startDate);
		            if (result2 < 0) {
		            	model.setErrors("The end date must be greater than the start date");
		            	request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
		            	return;
		            } 
		            if (result1 < 0) {
		            	model.setErrors("Task start date must be greater than the project start date");
		            	request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
		            	return;
		            } 

	                existingTask.setDescription(taskDescription);
	                existingTask.setStartDate(startDate);
	                existingTask.setEndDate(endDate);
	                
	                if (existingTask.getDescription().isEmpty() || existingTask.getStartDate() == null || existingTask.getEndDate() == null) {
			        	model.setErrors("Invalid task details");
			            request.getRequestDispatcher("/WEB-INF/task/failedUpdateTask.jsp").forward(request, response);
			            return;
			        }
	                metierT.updateTask(existingTask);
	                model.setTask(existingTask);
	                request.getRequestDispatcher("/WEB-INF/task/successUpdateTask.jsp").forward(request, response);
	            } else {
	                model.setErrors("Task not found for update.");
	                request.getRequestDispatcher("/WEB-INF/task/failedUpdateTask.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/task/failedUpdateTask.jsp").forward(request, response);
	        }
	    }


	    private void handleCreateAction(HttpServletRequest request, HttpServletResponse response, TaskModel model)
	            throws ServletException, IOException {
	        try {
	        	model.setMode("create");
	            String projectCode = request.getParameter("taskProject");
	            String taskCode = request.getParameter("taskCode");
	            if (metierT.getTask(taskCode) != null) {
	            	model.setErrors("Task already exists");
	            	request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
	            	return;
	            }
	            String taskDescription = request.getParameter("taskDescription");
	            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
	            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
	            int result1 = startDate.compareTo(metierP.getProject(projectCode).getStartDate());
	            int result2 = endDate.compareTo(startDate);
	            if (result2 < 0) {
	            	model.setErrors("The end date must be greater than the start date");
	            	request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
	            	return;
	            } 
	            if (result1 < 0) {
	            	model.setErrors("Task start date must be greater than the project start date");
	            	request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
	            	return;
	            } 

            	Task task = new Task();
	            task.setCode(taskCode);
	            task.setDescription(taskDescription);
	            task.setStartDate(startDate);
	            task.setEndDate(endDate);
	            task.setProject(metierP.getProject(projectCode));
		        if (task.getCode().isEmpty() || task.getDescription().isEmpty() || task.getStartDate() == null || task.getEndDate() == null) {
		        	model.setErrors("Invalid task details");
		            request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
		            return;
		        }
	            metierP.addTaskToProject(task);
	            model.setTask(task);
	            request.getRequestDispatcher("/WEB-INF/task/successInsertTask.jsp").forward(request, response);
	                
	             
	        } catch (Exception e) {
	            model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/task/failedInsertTask.jsp").forward(request, response);
	        }
	    }


	    private void handleSeeAllAction(HttpServletRequest request, HttpServletResponse response, TaskModel model)
	            throws ServletException, IOException {
	    	model.setMode("taskView");
	        model.setTasks(metierT.listTasks());
	        request.getRequestDispatcher("/WEB-INF/task/listTasks.jsp").forward(request, response);
	    }
	    
	    private void handleSearchAction(HttpServletRequest request, HttpServletResponse response, TaskModel model)
	            throws ServletException, IOException {
	    	String taskCode = request.getParameter("taskCode");
	    	Task existingtask = metierT.getTask(taskCode);
	    	if (existingtask != null ) {
	    		model.setMode("search");
		    	model.setTask(existingtask);
		    	request.getRequestDispatcher("/WEB-INF/task/task.jsp").forward(request, response);
	        }
	        else {
	            model.setErrors("Task not found for code: " + taskCode);
	            request.getRequestDispatcher("/WEB-INF/task/failedUpdateTask.jsp").forward(request, response);
	        }
	    }
	    

	}
