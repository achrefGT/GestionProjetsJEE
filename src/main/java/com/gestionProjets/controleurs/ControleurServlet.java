package com.gestionProjets.controleurs;

import java.io.IOException;

import com.gestionProjets.model.HomeModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ControleurProjectServlet
 */
@WebServlet("/ControleurServlet")
public class ControleurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() throws ServletException {
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurServlet() {
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
		 HomeModel model = new HomeModel();
	        request.setAttribute("model", model);
	        String view = request.getParameter("view");

	        if (view != null) {
	            switch (view) {
	                case "project":
	                	request.getRequestDispatcher("/projectController").forward(request, response);
	                    return;
	                case "task":
	                	request.getRequestDispatcher("/taskController").forward(request, response);
	                    return;
	                default:
	                    break;
	            }
	        }
	        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	    }
}