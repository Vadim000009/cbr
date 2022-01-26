package ru.cbrtb.cbrinterntb.model;

import lombok.Data;

import java.sql.Date;

@Data
public class TelephoneBook {
    private String lastname;
    private String firstname;
    private String workphone;
    private String mobilephone;
    private String mail;
    private Date birthday;
}
