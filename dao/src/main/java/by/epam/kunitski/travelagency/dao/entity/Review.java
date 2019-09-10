package by.epam.kunitski.travelagency.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class Review {

    @Id
    private String id;

    @PastOrPresent
    private LocalDate date;

    @Size(min = 1, max = 500)
    @NotEmpty
    private String text;

    @NotNull
    @DBRef
    private User user;

    @NotNull
    @DBRef
    private Tour tour;
}