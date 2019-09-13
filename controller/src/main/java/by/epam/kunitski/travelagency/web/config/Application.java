package by.epam.kunitski.travelagency.web.config;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.dao.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = { "by.epam.kunitski.travelagency" } )
@EnableMongoRepositories("by.epam.kunitski.travelagency.dao.repository")
public class Application implements CommandLineRunner {

    @Autowired
    private UserDAO userDAO;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

       @Override
    public void run(String... args) throws Exception {
//        User user = userDAO.findByUsername("");
//
//           System.out.println( userDAO.save(user));
    }

}
