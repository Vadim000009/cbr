package ru.cbrpw.cbrinternpw.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.cbrpw.cbrinternpw.model.PhoneBook;
import ru.cbrpw.cbrinternpw.services.PhoneBookService;

import java.util.List;

@RestController
@RequestMapping(value = "/tb")
public class PhoneBookController {
    private final PhoneBookService phoneBookService;

    public PhoneBookController (PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> addUser() {
        phoneBookService.checkNewFileAndAdd();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    public ResponseEntity<List> getAll() {
        List <PhoneBook> list = phoneBookService.getAllPhoneBook();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }
}
