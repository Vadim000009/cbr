package ru.cbrtb.cbrinterntb.services;

import org.springframework.stereotype.Service;
import ru.cbrtb.cbrinterntb.api.StringResponse;
import ru.cbrtb.cbrinterntb.dao.TelephoneBookDAO;
import ru.cbrtb.cbrinterntb.model.TelephoneBook;

@Service
public class TelephoneBookService {
    private TelephoneBookDAO telephoneBookDAO = new TelephoneBookDAO();

    public StringResponse addNewRecord(TelephoneBook telephoneBook) {
        return telephoneBookDAO.prepareJSON(telephoneBook);
    }

    public Boolean moveOrDeleteRecord(StringResponse stringResponse, Boolean bool) {
        return telephoneBookDAO.workJSON(stringResponse, bool);
    }

}
