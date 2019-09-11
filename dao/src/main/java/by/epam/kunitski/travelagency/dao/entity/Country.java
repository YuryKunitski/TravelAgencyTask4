package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Country {

    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String name;

}