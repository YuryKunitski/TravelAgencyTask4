package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

@Data
public class Hotel {

    @Id
    private Integer id;

    @NotNull
    private String name;

    @Range(min = 1, max = 5)
    private int stars;

    @URL
    private String website;

    @Range(min = -90, max = 90)
    private double latitude;

    @Range(min = -180, max = 180)
    private double longitude;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Type(type = "pgsql_enum")
    private FeatureType features;

    public enum FeatureType {
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

}
