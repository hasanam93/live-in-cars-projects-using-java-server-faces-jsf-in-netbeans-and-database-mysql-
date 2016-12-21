package others;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/*@ManagedBean(name="message")
@javax.faces.bean.SessionScoped*/
public class Message {
	
	
	public Date time;
	
	
	public User user;
	
	
	public String text;
	
	public Message(User user, String text){
		this.user = user;
		this.text = text;
		this.time = new Date();
	}

	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeOfMessage = sdf.format(time);
		return timeOfMessage;
	}

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}
	
	
	
}
