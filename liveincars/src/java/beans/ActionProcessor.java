package beans;

import java.util.ResourceBundle;
import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import others.User;


@RequestScoped
@Named("actionProcessor")
public class ActionProcessor {

    

	private @Inject Chat chat;
	private @Inject UserSession userSession;
	private ResourceBundle rb = ResourceBundle.getBundle("others/messages");

	public ActionProcessor() {

	}

	 public String createNewUser() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		String userName = (String) fc.getExternalContext().getRequestParameterMap().get("name");
		String userColor = (String) fc.getExternalContext().getRequestParameterMap().get("color");
		if(userName==null || userColor==null){
			fc.addMessage("warnings",
					new FacesMessage(rb.getString("welcome.user.errorprocessinginputs")));
			return null;
		}
		User user = new User(userColor, userName);
		if (chat.addNewUser(user)) {
			userSession.setUser(user);
			userSession.setSession(session);
			userSession.updateLastAccessTime();

			chat.addMessage(user, rb.getString("chatroom.user.comein"));
			return "chatroom.xhtml?faces-redirect=true";
		}
		fc.addMessage("warnings",
				new FacesMessage(rb.getString("welcome.user.alreadyinchat")));
		return null;
	}

	
	public String leaveChat() {
		chat.addMessage(userSession.getUser(),
				rb.getString("chatroom.user.leave"));
		userSession.destroySession();
		return "welcome.xhtml?faces-redirect=true";
	}

	
	public String sendMessage() {
		userSession.updateLastAccessTime();
		FacesContext fc = FacesContext.getCurrentInstance();
		String message = (String)fc.getExternalContext().getRequestParameterMap().get("output_message");
		if(message!=null){
			chat.addMessage(userSession.getUser(), message);
		}
		return null;
	}
}
