package ru.cbrpw.cbrinternpw.util;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogFile {
    @Value("${paths.log}")
    private String logLocation = "log";

    @Value("${paths.logFile}")
    private String logFileName = "log.txt";
    private String path = logLocation + "\\" + logFileName;
    private Logger logFile = Logger.getLogger(getClass().getName());
    private FileHandler fileHandler = null;

    public LogFile() {
        try {
            File folder = new File(logLocation);
            if (!folder.exists()) folder.mkdir();
            fileHandler = new FileHandler(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        logFile.addHandler(fileHandler);
    }

    public void info(String message) {
        logFile.log(Level.INFO, message + "\n");
    }

    public void severe(Exception ex, String message) {
        logFile.log(Level.SEVERE, message + "\n" + ex + "\n");
    }
}
