package dca0120.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SairServlet extends HttpServlet {


	private static final long serialVersionUID = -7552123270167571493L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("usuario") != null) {
			session.setAttribute("usuario", null);
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath());
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