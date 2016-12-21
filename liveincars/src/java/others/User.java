package others;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*@ManagedBean(name="user")
@SessionScoped*/
public class User {
	
	public String color;
	
	
	public String name;
	
	
	public long lastAccessTime; 
	

	public User(String color, String name){
		this.color = color;
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}
	
	public long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if(obj instanceof User){
			User other = (User)obj;
			return other.color.equals(color) && other.name.equals(name);
		}
		return false;		
	}
	@Override
	public int hashCode(){
		return (color.length() + name.length())*name.charAt(0);
	}
	
	

}
