package by.epam.kunitski.travelagency.dao.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Integer id;

    @Size(min = 3, max = 20)
    private String login;

    //    @Size(min = 3, max = 20)
    @NotNull
    private String password;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Type( type = "pgsql_enum" )
    private UserRole role;

    public enum UserRole {
        MEMBER, ADMIN
    }

}

