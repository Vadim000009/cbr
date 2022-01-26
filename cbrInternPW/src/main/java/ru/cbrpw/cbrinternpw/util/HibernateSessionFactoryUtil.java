package ru.cbrpw.cbrinternpw.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.cbrpw.cbrinternpw.model.PhoneBook;
import ru.cbrpw.cbrinternpw.model.PlaceOfWork;

public class HibernateSessionFactoryUtil {
    private static LogFile cbr = new LogFile();
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(PlaceOfWork.class);
                configuration.addAnnotatedClass(PhoneBook.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                cbr.info("Запущена SessionFactory");
            } catch (Exception e) {
                cbr.severe(e, "Ошибка при работе с SessionFactory");
            }
        }
        return sessionFactory;
    }
}
