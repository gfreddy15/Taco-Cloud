package tacos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
// import org.springframework.data.annotation.Id; This is used only with JDBC Implementation
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * The commented Method worked with Spring Data and JDBC
 * Active method is Spring JPA
 */
/*
@Data
@Table
public class Taco {

    @Id
    private Long id;

    private Date createdAt = new Date();

    @NonNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<IngredientRef> ingredients;

    public Taco() {

    }
}
*/
@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NonNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    @ManyToMany
    private List<IngredientRef> ingredients;

    public Taco() {

    }
}