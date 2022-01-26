package ru.cbrpw.cbrinternpw.dao;

import com.google.gson.Gson;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Value;
import ru.cbrpw.cbrinternpw.model.PhoneBook;
import ru.cbrpw.cbrinternpw.model.PlaceOfWork;
import ru.cbrpw.cbrinternpw.util.HibernateSessionFactoryUtil;
import ru.cbrpw.cbrinternpw.util.LogFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PhoneBookDAO {
    private LogFile cbr = new LogFile();

    @Value("${paths.work}")
    private String workLocation = "work";

    @Value("${paths.data}")
    private String dataLocation = "data";
    private String dirloc = "C:\\Users\\1\\IdeaProjects\\cbrInternTB\\";

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    public Boolean search() {
        File dir = new File(dirloc + workLocation);
        List<File> fileListReady = new ArrayList<>();
        List<File> fileListTXT = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile() && (getFileExtension(file).equals("ready"))) {
                fileListReady.add(file);
            } else if (file.isFile() && (getFileExtension(file).equals("txt"))) {
                fileListTXT.add(file);
            }
        }
        for (int i = 0; i <= fileListReady.size() - 1; i++) {
            for (int j = 0; j <= fileListTXT.size() - 1; j++) {
                if (fileListTXT.get(j).toString().substring(0, fileListTXT.get(j).toString().length() - 3).equals(fileListReady.get(i).toString().substring(0, fileListReady.get(i).toString().length() - 5))) {
                    try {
                        // Я уверен, что можно красивее, но получилось как всегда...
                        Files.move(Paths.get(dirloc + workLocation + "\\" + fileListTXT.get(j).getName()), Paths.get(dirloc + dataLocation + "\\" + fileListTXT.get(j).getName()));
                        fileListReady.get(i).delete();
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(dirloc + dataLocation + "\\" + fileListTXT.get(j).getName()));
                        Gson gson = new Gson();
                        HashMap<String, String> json = gson.fromJson(bufferedReader, HashMap.class);
                        String lastname = json.get("lastname");
                        String firstname = json.get("firstname");
                        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                        List <Object[]> listObject = session.createSQLQuery("select placeofwork, addressofwork from placeofwork where lastname='" + lastname +"' and firstname='" + firstname +"'").list();
                        PlaceOfWork placeOfWork = new PlaceOfWork();
                        Iterator it = listObject.iterator();
                        Object[] line = (Object[]) it.next();
                        placeOfWork.setAddressOfWork((String) line[0]);
                        placeOfWork.setPlaceOfWork((String) line[1]);
                        json.put("work", placeOfWork.getAddressOfWork()+ "\n" + placeOfWork.getPlaceOfWork());
                        Writer writer = new FileWriter(dirloc + dataLocation + "\\" + fileListTXT.get(j).getName(), false);
                        gson.toJson(json, writer);
                        writer.close();
                        LocalDate localDate = LocalDate.parse(json.get("birthday"), DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault()));
                        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                        PhoneBook phoneBook = new PhoneBook(json.get("lastname"), json.get("firstname"), json.get("workphone"),
                                json.get("mobilephone"), json.get("mail"), date, json.get("work"));
                        addNewRecord(phoneBook);
                        cbr.info("Сохранение в файле 'Телефонная книга'");
                    } catch (IOException e) {
                        cbr.severe(e, "Ошибка при сохранении в файле 'Телефонная книга'. Возможна потеря данных");
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public void addNewRecord(PhoneBook phoneBook) {
        try {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(phoneBook);
            transaction.commit();
            session.close();
            cbr.info("Сохранение в справочнике 'Телефонная книга'");
        } catch (HibernateException e) {
            cbr.severe(e, "Ошибка при сохранении в справочнике 'Телефонная книга'");
        }
    }

    public List <PhoneBook> getAllFromPhoneBook() {
        cbr.info("Получение всех данных из справочника 'Телефонная книга'");
        return (List <PhoneBook>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createNativeQuery("select lastname, firstname, workphone, mobilephone, birthdate, work from phonebook").list();
    }
}
