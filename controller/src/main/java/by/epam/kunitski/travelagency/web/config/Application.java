package by.epam.kunitski.travelagency.web.config;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.repository.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = { "by.epam.kunitski.travelagency" } )
@EnableMongoRepositories("by.epam.kunitski.travelagency.dao.repository")
public class Application implements CommandLineRunner {

//    @Autowired
//    private CountryDAO countryDAO;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

       @Override
    public void run(String... args) throws Exception {

    }

}
