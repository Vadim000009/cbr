package ru.cbrtb.cbrinterntb.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import ru.cbrtb.cbrinterntb.CbrInternTbApplication;
import ru.cbrtb.cbrinterntb.api.StringResponse;
import ru.cbrtb.cbrinterntb.model.TelephoneBook;
import ru.cbrtb.cbrinterntb.util.LogFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TelephoneBookDAO {
    private LogFile cbr = CbrInternTbApplication.logger;

    // перенести в yaml файл
    @Value("${paths.prepare}")
    private String prepareLocation = "prepare";

    @Value("${paths.work}")
    private String workLocation = "work";

    @Value("${paths.data}")
    private String dataLocation = "data";

    public void checkLocation(){
        String[] arrayOfStrings = new String[] {prepareLocation, workLocation, dataLocation};
        for (int i = 0; i <= arrayOfStrings.length - 1; i++){
            File folder = new File(arrayOfStrings[i]);
            if (!folder.exists()) {
                folder.mkdir();
                cbr.info("Создана директория " + arrayOfStrings[i]);
            }
        }
    }

    public StringResponse prepareJSON(TelephoneBook tbs){
        checkLocation();
        String timeStamp = new SimpleDateFormat("ddMMyy_hhmm").format(Calendar.getInstance().getTime());
        StringBuilder JSONPrepareName = new StringBuilder();
        JSONPrepareName.append("\\data_").append(timeStamp).append(".json");
        try (Writer writer = new FileWriter(prepareLocation + JSONPrepareName, false)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(tbs, writer);
            cbr.info(JSONPrepareName + " успешно создан в директории " + prepareLocation);
        } catch (IOException e) {
            cbr.severe(e, JSONPrepareName + " не удалось создать в директории " + prepareLocation);
        }
        return new StringResponse(JSONPrepareName.toString());
    }

    public Boolean workJSON(StringResponse JSONPrepareName, Boolean action){
        StringBuilder src = new StringBuilder();
        src.append(prepareLocation).append(JSONPrepareName.toStringWithNoJSON());

        if(action) {
            StringBuilder srcReady = new StringBuilder();
            StringBuilder dest = new StringBuilder();
            StringBuilder JSONWorkName = new StringBuilder();
            String timeStamp = new SimpleDateFormat("ddMMyy_hhmm").format(Calendar.getInstance().getTime());

            JSONWorkName.append("\\json_").append(timeStamp);
            srcReady.append(JSONWorkName).append(".ready");
            JSONWorkName.append(".txt");
            dest.append(workLocation).append("\\").append(JSONWorkName);

            try (Writer writer = new FileWriter(workLocation + srcReady, false)) {
                Files.move(Paths.get(src.toString()), Paths.get(dest.toString()));
                cbr.info(JSONPrepareName.toStringWithNoJSON() + " успешно перемещён и теперь имеет имя " + JSONWorkName);
                return true;
            } catch (IOException e) {
                cbr.severe(e, JSONPrepareName.toStringWithNoJSON() + " не удалось переместить и переименовать в " + JSONWorkName);
                return false;
            }
        } else {
            try {
                Files.deleteIfExists(Paths.get(src.toString()));
                cbr.info(JSONPrepareName.toStringWithNoJSON() + " успешно удалён");
                return true;
            } catch (IOException e) {
                cbr.severe(e, JSONPrepareName.toStringWithNoJSON() + " не удалось удалить");
                return false;
            }
        }
    }
}
