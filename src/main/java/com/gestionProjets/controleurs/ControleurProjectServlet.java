package com.gestionProjets.controleurs;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.gestionProjets.entities.*;
import com.gestionProjets.metier.*;
import com.gestionProjets.model.ProjectModel;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControleurProjectServlet
 */
@WebServlet("/ControleurProjectServlet")
public class ControleurProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
	private ProjectDAO metierP;
    @EJB
	private TaskDAO metierT;
	
	
	@Override
	public void init() throws ServletException {
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurProjectServlet() {
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
	        ProjectModel model = new ProjectModel();
	        request.setAttribute("model", model);

	        String action = request.getParameter("action");
	        String mode = request.getParameter("mode");
	        model.setMode(mode);
	        if ("update".equals(mode)) {
	        	String code = request.getParameter("projectCode");
	        	model.setMode(mode);
	        	model.setProject(metierP.getProject(code));
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
	                case "projectTasks":
	                    handleSeeTasksAction(request, response, model);
	                    return;
	                case "removeTask":
	                    handleRemoveTaskAction(request, response, model);
	        	    	return;
	                case "addTask":
	                    handleAddTaskAction(request, response, model);
	                    return;
	                case "search":
	                    handleSearchAction(request, response, model);
	                    return;
	                default:
	                    break;
	            }
	        }
	        request.getRequestDispatcher("/WEB-INF/project/project.jsp").forward(request, response);
	    }

	    private void handleRemoveAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	        try {
	        	String code = request.getParameter("projectCode");
		        Project existingProject = metierP.getProject(code);
		        if (existingProject != null ) {
		        	model.setProject(existingProject);
		        	request.getRequestDispatcher("/WEB-INF/project/successRemoveProject.jsp").forward(request, response);
		        	metierP.deleteProject(code);
		        }
		        else {
		            model.setErrors("Project not found for code: " + code);
		            request.getRequestDispatcher("/WEB-INF/project/failedRemoveProject.jsp").forward(request, response);
		        }
	        } catch (Exception e) {
	        	model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/project/failedRemoveProject.jsp").forward(request, response);
			}
	    	
	    }

	    private void handleUpdateAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	try {
	    		String code = request.getParameter("projectCode");
	    		if (code == null) {
	    			model.setMode("simple");
	    			model.setErrors("Invalid code");
	    			request.getRequestDispatcher("/WEB-INF/project/failedUpdateProject.jsp").forward(request, response);
	    			return;
	    		}
		        Project existingProject = metierP.getProject(code);

		        if (existingProject != null) {
		            existingProject.setDescription(request.getParameter("projectDescription"));
		            existingProject.setStartDate(LocalDate.parse(request.getParameter("startDate")));			    
			        if (existingProject.getDescription().isEmpty() || existingProject.getStartDate() == null) {
			        	model.setErrors("Invalid project details");
			            request.getRequestDispatcher("/WEB-INF/project/failedUpdateProject.jsp").forward(request, response);
			            return;
			        }
		            metierP.updateProject(existingProject);
		            model.setProject(existingProject);
		            request.getRequestDispatcher("/WEB-INF/project/successUpdateProject.jsp").forward(request, response);
		        }
		        else {
		            model.setErrors("Project not found for code: " + code);
		            request.getRequestDispatcher("/WEB-INF/project/failedUpdateProject.jsp").forward(request, response);
		        }
		        
	        } catch (Exception e) {
	        	model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/project/failedUpdateProject.jsp").forward(request, response);
			}
	    }

	    private void handleCreateAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	try {
	    		String projectCode = request.getParameter("projectCode");
	    		if (metierP.getProject(projectCode) != null) {
	            	model.setErrors("Project already exists");
	            	request.getRequestDispatcher("/WEB-INF/project/failedInsertProject.jsp").forward(request, response);
	            	return;
	            }
	    		model.getProject().setCode(projectCode);
		        model.getProject().setDescription(request.getParameter("projectDescription"));
		        model.getProject().setStartDate(LocalDate.parse(request.getParameter("startDate")));
		        Project p = model.getProject();
		        if (p.getCode().isEmpty() || p.getDescription().isEmpty() || p.getStartDate() == null) {
		        	model.setErrors("Invalid project details");
		            request.getRequestDispatcher("/WEB-INF/project/failedInsertProject.jsp").forward(request, response);
		            return;
		        }
		        metierP.addProject(p);
		        model.setProject(p);
		        request.getRequestDispatcher("/WEB-INF/project/successInsertProject.jsp").forward(request, response);
	        } catch (Exception e) {
	        	model.setErrors(e.getMessage());
	            request.getRequestDispatcher("/WEB-INF/project/failedInsertProject.jsp").forward(request, response);
			}
	    }

	    private void handleSeeAllAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	        model.setProjects(metierP.listProjects());
	    	request.getRequestDispatcher("/WEB-INF/project/listProjects.jsp").forward(request, response);  
	    }
	    
	    private void handleRemoveTaskAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	String taskCode = request.getParameter("taskCode"); 
	    	Task t = metierT.getTask(taskCode);
	    	model.setTask(t);
	        metierP.removeTaskFromProject(t);
	        request.getRequestDispatcher("/WEB-INF/task/successRemoveTask.jsp").forward(request, response);
	    }
	    private void handleAddTaskAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	String projectCode = request.getParameter("projectCode");
	    	model.setProject(metierP.getProject(projectCode));
	    	model.setMode("addTaskToProject");
	    	request.getRequestDispatcher("/WEB-INF/task/task.jsp").forward(request, response);

	    }
	    private void handleSeeTasksAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	String projectCode = request.getParameter("projectCode");
	    	Project p = metierP.getProject(projectCode);
	    	
	    	model.setMode("projectView");
	        model.setTasks((List<Task>) p.getTasks());
	        model.setProject(p);
	        
	        request.getRequestDispatcher("/WEB-INF/task/listTasks.jsp").forward(request, response);
	    }
	    private void handleSearchAction(HttpServletRequest request, HttpServletResponse response, ProjectModel model) throws ServletException, IOException {
	    	String projectCode = request.getParameter("projectCode");
	    	Project existingProject = metierP.getProject(projectCode);
	    	if (existingProject != null ) {
	    		model.setMode("search");
		    	model.setProject(existingProject);
		    	request.getRequestDispatcher("/WEB-INF/project/project.jsp").forward(request, response);
	        }
	        else {
	            model.setErrors("Project not found for code: " + projectCode);
	            request.getRequestDispatcher("/WEB-INF/project/failedUpdateProject.jsp").forward(request, response);
	        }
	    }
	    
	}
