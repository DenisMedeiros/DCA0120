package dca0120.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaginaInicialServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571193L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		
		
        request.getRequestDispatcher("/inicio.jsp").forward(request, response);
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = null;

        if ((username.equals("kiran")) && (password.equals("kiran"))) {
            message = "Welcome "+username+" thanks for login...";
        } else {
            message = "You are not the valid user...";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

} 