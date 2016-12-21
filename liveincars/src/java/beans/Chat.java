package beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import others.Message;
import others.User;


@Singleton
@ApplicationScoped
@Named(value = "chat")
public class Chat {

	
	private Set<User> users = new HashSet<User>();;
	private Set<User> onlineUsers = new HashSet<User>();;
	
	private NavigableMap<Long, Message> messages = new TreeMap<Long, Message>();;

	public Chat() {

	}

	
	public boolean addNewUser(User user) {
		return users.add(user);
	}

	
	public void addMessage(User user, String text) {
		Message message = new Message(user, text);
		messages.put(System.currentTimeMillis(), message);
	}

	
	public Set<User> getUsers() {
		return users;
	}

	
	public NavigableMap<Long, Message> getMessages() {
		return messages;
	}

	
	@Schedule(hour = "*", minute = "*", second = "*/5")
	public void updateOnlineUsers() {
		Set<User> freshSetOfUsersOnline = new HashSet<>();
		long now = System.currentTimeMillis();
		for (User user : users) {
			if (now - user.getLastAccessTime() < 5000) {
				freshSetOfUsersOnline.add(user);
			}
		}
		this.onlineUsers = freshSetOfUsersOnline;
	}

	
	public List<User> getOnlineUsers() {
		return new ArrayList(onlineUsers);
	}

	
	public void removeUser(User user) {
		users.remove(user);
		onlineUsers.remove(user);
	}

}
