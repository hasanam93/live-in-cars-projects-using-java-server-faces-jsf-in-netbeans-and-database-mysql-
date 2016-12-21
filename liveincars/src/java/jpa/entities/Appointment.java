/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Student
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByFirstname", query = "SELECT a FROM Appointment a WHERE a.firstname = :firstname"),
    @NamedQuery(name = "Appointment.findByLastname", query = "SELECT a FROM Appointment a WHERE a.lastname = :lastname"),
    @NamedQuery(name = "Appointment.findByPhone", query = "SELECT a FROM Appointment a WHERE a.phone = :phone"),
    @NamedQuery(name = "Appointment.findByEmail", query = "SELECT a FROM Appointment a WHERE a.email = :email"),
    @NamedQuery(name = "Appointment.findByModel", query = "SELECT a FROM Appointment a WHERE a.model = :model"),
    @NamedQuery(name = "Appointment.findByBrand", query = "SELECT a FROM Appointment a WHERE a.brand = :brand"),
    @NamedQuery(name = "Appointment.findByServices", query = "SELECT a FROM Appointment a WHERE a.services = :services"),
    @NamedQuery(name = "Appointment.findByMessage", query = "SELECT a FROM Appointment a WHERE a.message = :message"),
    @NamedQuery(name = "Appointment.findByDate", query = "SELECT a FROM Appointment a WHERE a.date = :date"),
    @NamedQuery(name = "Appointment.findByTime", query = "SELECT a FROM Appointment a WHERE a.time = :time")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "firstname")
    private String firstname;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone")
    private long phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "services")
    private String services;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    public Appointment() {
    }

    public Appointment(String lastname) {
        this.lastname = lastname;
    }

    public Appointment(String lastname, String firstname, long phone, String email, String model, String brand, String services, String message, Date date, Date time) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.email = email;
        this.model = model;
        this.brand = brand;
        this.services = services;
        this.message = message;
        this.date = date;
        this.time = time;
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lastname != null ? lastname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.lastname == null && other.lastname != null) || (this.lastname != null && !this.lastname.equals(other.lastname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Appointment[ lastname=" + lastname + " ]";
    }
    
}
