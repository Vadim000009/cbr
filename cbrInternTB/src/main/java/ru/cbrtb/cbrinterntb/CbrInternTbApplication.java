package ru.cbrtb.cbrinterntb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import ru.cbrtb.cbrinterntb.util.LogFile;

@SpringBootApplication
@EnableCaching
public class CbrInternTbApplication {
    public static LogFile logger = new LogFile();
    //-Dspring.config.location=file:C:\Users\1\IdeaProjects\settings.yml
    public static void main(String[] args) {
        SpringApplication.run(CbrInternTbApplication.class, args);
    }

}
