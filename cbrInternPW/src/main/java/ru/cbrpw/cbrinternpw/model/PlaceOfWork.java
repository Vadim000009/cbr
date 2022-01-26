package ru.cbrpw.cbrinternpw.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "placeofwork")
public class PlaceOfWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lastname" ,length = 20)
    private String lastname;

    @Column(name = "firstname" ,length = 10)
    private String firstname;

    @Column(name = "placeofwork" ,length = 250)
    private String placeOfWork;

    @Column(name = "addressofwork" ,length = 250)
    private String addressOfWork;

    public PlaceOfWork (String lastname, String firstname, String placeOfWork, String addressOfWork) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.placeOfWork = placeOfWork;
        this.addressOfWork = addressOfWork;
    }

    public PlaceOfWork(String placeOfWork, String addressOfWork) {
        this.placeOfWork = placeOfWork;
        this.addressOfWork = addressOfWork;
    }

    public PlaceOfWork(Long id, String lastname, String firstname, String placeOfWork, String addressOfWork) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.placeOfWork = placeOfWork;
        this.addressOfWork = addressOfWork;
    }

    public PlaceOfWork() {}

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

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getAddressOfWork() {
        return addressOfWork;
    }

    public void setAddressOfWork(String addressOfWork) {
        this.addressOfWork = addressOfWork;
    }
}
