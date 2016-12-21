package servlets;

import java.io.IOException;
import java.io.Writer;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserSession;


@WebServlet("/readmessages")
public class ReadMessages extends HttpServlet {
@Inject
	private  UserSession userSession;
	private ResourceBundle rb = ResourceBundle.getBundle("others/messages");

	public ReadMessages() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		
		if (userSession.getUser() != null) {
			
			getServletContext().getRequestDispatcher("/messages.xhtml")
					.forward(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.setStatus(200);
			Writer wr = response.getWriter();
			wr.write("<a href=\"welcome.xhtml\" style=\"color: peru;\">"
					+ rb.getString("chatroom.session.end") + "</a>");
			wr.flush();
			wr.close();
			response.flushBuffer();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
