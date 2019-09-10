package by.epam.kunitski.travelagency.dao.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Size(min = 3, max = 20)
    @Indexed(unique=true)
    private String username;

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

