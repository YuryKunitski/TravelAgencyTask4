package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class Country {

   @Id
    private String id;

    @Indexed(unique=true)
    private String name;

}