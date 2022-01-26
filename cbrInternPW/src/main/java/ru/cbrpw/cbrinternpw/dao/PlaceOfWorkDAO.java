package ru.cbrpw.cbrinternpw.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.cbrpw.cbrinternpw.CbrInternPwApplication;
import ru.cbrpw.cbrinternpw.model.PlaceOfWork;
import ru.cbrpw.cbrinternpw.util.HibernateSessionFactoryUtil;
import ru.cbrpw.cbrinternpw.util.LogFile;

import java.util.List;

public class PlaceOfWorkDAO {
    private LogFile cbr = new LogFile();

    public void save(PlaceOfWork placeOfWork) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(placeOfWork);
            transaction.commit();
            session.close();
            cbr.info("Сохранение в справочнике 'место работы'");
        } catch (HibernateException e) {
            cbr.severe(e, "Ошибка при сохранении в справочнике 'место работы'");
        }
    }

    public List <PlaceOfWork> getAllFromPlaceOfWork() {
        cbr.info("Получение всех данных из справочника 'Место работы'");
        return (List <PlaceOfWork>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createNativeQuery("select lastname, firstname, placeOfWork, addressOfWork from placeofwork").list();
    }
}
