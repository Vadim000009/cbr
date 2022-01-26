package ru.cbrpw.cbrinternpw.repository;

import org.springframework.data.repository.CrudRepository;
import ru.cbrpw.cbrinternpw.model.PhoneBook;

public interface PhoneBookRepository extends CrudRepository<PhoneBook, Integer> {
}
