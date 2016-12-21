package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import others.Message;
import others.User;


@SessionScoped
@Named("userSession")
public class UserSession implements Serializable {

	private @Inject Chat chat;
	private User user;
	private HttpSession session;

	public UserSession() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public long getLastAccessTime() {
		return user.getLastAccessTime();
	}

	public void updateLastAccessTime() {
		user.setLastAccessTime(System.currentTimeMillis());
	}

	
	public List<Message> getUnreadedMessages() {
		Long start = chat.getMessages().ceilingKey(user.getLastAccessTime());
		updateLastAccessTime();
		if (start != null) {
			Map<Long, Message> unReadedmessages = chat.getMessages().tailMap(
					start);
			return new ArrayList<Message>(unReadedmessages.values());
		} else
			return new ArrayList<Message>();
	}

	
	public void destroySession() {
		chat.removeUser(user);
		user = null;
		session.invalidate();
		session = null;
	}

}
