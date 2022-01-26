package ru.cbrpw.cbrinternpw.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phonebook")
public class PhoneBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lastname" ,length = 20)
    private String lastname;

    @Column(name = "firstname" ,length = 10)
    private String firstname;

    @Column(name = "workphone" ,length = 11)
    private String workphone;

    @Column(name = "mobilephone" ,length = 11)
    private String mobilephone;

    @Column(name = "mail" ,length = 41)
    private String mail;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "work" ,length = 501)
    private String work;


    public PhoneBook(String lastname, String firstname, String workphone, String mobilephone, String mail, Date birthdate, String work) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.workphone = workphone;
        this.mobilephone = mobilephone;
        this.mail = mail;
        this.birthdate = birthdate;
        this.work = work;
    }

    public PhoneBook() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
