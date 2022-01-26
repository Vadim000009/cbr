package ru.cbrpw.cbrinternpw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cbrpw.cbrinternpw.dao.PhoneBookDAO;
import ru.cbrpw.cbrinternpw.model.PhoneBook;
import ru.cbrpw.cbrinternpw.repository.PhoneBookRepository;

import java.util.List;

@Service
public class PhoneBookService {
    private PhoneBookDAO phoneBookDAO = new PhoneBookDAO();
    private PhoneBookRepository pbr;

    @Autowired
    public PhoneBookService(PhoneBookRepository pbrs) {
        this.pbr = pbrs;
    }

    public void checkNewFileAndAdd() {
        phoneBookDAO.search();
    }

    public List <PhoneBook> getAllPhoneBook() {
        return phoneBookDAO.getAllFromPhoneBook();
    }

}
