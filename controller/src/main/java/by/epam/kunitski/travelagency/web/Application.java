package by.epam.kunitski.travelagency.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@SpringBootApplication
@EnableMongoRepositories("by.epam.kunitski.travelagency.dao.repository")
//@EnableOAuth2Client
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
