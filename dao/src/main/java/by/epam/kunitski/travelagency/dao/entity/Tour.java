package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Data
public class Tour {

    @Id
    private String id;

    private String photo;

    @FutureOrPresent
    private LocalDate date;

    @Range(min = 1, max = 99)
    private Integer duration;

    @Size(min = 1, max = 500)
    private String description;

    @Positive
    private Double cost;

    @DBRef
    private Hotel hotel;

    @DBRef
    private Country country;

//    @Enumerated(EnumType.STRING)
//    @Type( type = "pgsql_enum" )
    private TourType tour_type;

    public enum TourType {
        ECONOM, ALL_INCLUSIVE, ONLY_BREAKFAST
    }

}

