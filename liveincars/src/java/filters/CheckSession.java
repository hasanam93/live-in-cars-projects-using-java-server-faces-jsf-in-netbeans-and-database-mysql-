package filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserSession;
import javax.ejb.EJB;


@WebFilter("/CheckSession")
public class CheckSession implements Filter {

	private @Inject UserSession userSession;

	public CheckSession() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String contextPath = req.getContextPath();
		HttpSession session = req.getSession();
		boolean inChat = userSession.getUser() != null;
		String fromWhere = req.getServletPath();
		if (inChat) {
			if (!fromWhere.contains("chatroom")) {
				res.sendRedirect(contextPath + "/chatroom.xhtml");
			} else
				chain.doFilter(request, response);
		} else {
			if (!fromWhere.contains("welcome")) {
				res.sendRedirect(contextPath + "/welcome.xhtml");
			} else
				chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
