/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import static javax.mail.Session.getInstance;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Student
 */
@ManagedBean(name="mail")
@RequestScoped
public class Mail {
  public String header;
    public String name;
    public String mailAddress;
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailaddress() {
        return mailAddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailAddress = mailaddress;
    }
  
    public void sendMail() throws MessagingException
    {
        final String username="";
        final String password="";
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        Session session =Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username,password);
                        
                }
});
        try
        {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("aa@aa.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hasanam93@gmail.com"));
            message.setSubject("Java Mail Api");
            message.setText(name + "!send you message from" + mailAddress +"which header="+header);
            Transport.send(message);
        
            

                }
        catch(MessagingException ex){
            throw new RuntimeException(ex);
        }
    }
}