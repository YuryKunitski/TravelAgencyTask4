package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Test  implements CommandLineRunner {

    @Autowired
    private UserDAO userDAO;

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


//        User user = new User();
//        user.setUsername("Aleks");
//
//        User user1 = new User();
//        user1.setUsername("Bob");
//
//        User user2 = new User();
//        user2.setUsername("Annita");
//
//        System.out.println("user - "+userDAO.save(user));
//        System.out.println("user1 - "+userDAO.save(user1));
//        System.out.println("user2 - "+userDAO.save(user2));

        System.out.println("user Bob - "+userDAO.findById("0"));
    }

}
