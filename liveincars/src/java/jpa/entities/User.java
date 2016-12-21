/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Student
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {
DataSource ds;
    private String dbName;
    private String dbPassword;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "password")
    private String password;

    public User() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:app/jdbc/appdb");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public User(String firstname) {
        this.firstname = firstname;
    }

    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (firstname != null ? firstname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.firstname == null && other.firstname != null) || (this.firstname != null && !this.firstname.equals(other.firstname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.User[ firstname=" + firstname + " ]";
    }
     public String add() {
        int i = 0;
        if (firstname != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                if (ds != null) {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "INSERT INTO user(firstname,lastname,email,password) VALUES(?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, firstname);
                        ps.setString(2, lastname);
                        ps.setString(3, email);
                        ps.setString(4, password);
                        i = ps.executeUpdate();
                        System.out.println("Data Added Successfully");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            return "success";
        } else
            return "unsuccess";
    }

    public void dbData(String uName) {
        if (uName != null) {
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs = null;

            if (ds != null) {
                try {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "select firstname,password from user where firstname = '"
                                + uName + "'";
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                        rs.next();
                        dbName = rs.getString("firstname");
                        dbPassword = rs.getString("password");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public String login() {
        dbData(firstname);
        if (firstname.equals(dbName) && password.equals(dbPassword)) {
            return "output";
        } else
            return "invalid";
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        FacesContext.getCurrentInstance()
                .getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
    }
    
}
