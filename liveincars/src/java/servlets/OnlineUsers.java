package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserSession;


@WebServlet("/onlineusers")
public class OnlineUsers extends HttpServlet {
@Inject
	private  UserSession userSession;

	public OnlineUsers() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (userSession.getUser() != null) {
			
			getServletContext().getRequestDispatcher("/users.xhtml").forward(
					request, response);
		} else {
			response.setStatus(200);
			response.flushBuffer();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
