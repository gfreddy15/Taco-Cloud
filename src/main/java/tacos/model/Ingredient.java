package tacos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
// import org.springframework.data.annotation.Id; Used for Spring JDBC  with Spring Data NOT Spring JPA
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


/**
 * @NoArgsConstructor(access= AccessLevel.PRIVATE, force = true): This make a no args constructor that is private and
 * always forced to instantiate even if other constructors are used.
 *      1. As a private No Args Con. Only this class can instantiate itself. This forces the application to leverage Factory
 *         classes to create an instance of this object.
 *      2. Forced set to true will always have this Con. available in runtime.
 *      3. Commented Code is for JDBC Implementation. Active code is Spring JPA Data
 *
 */
/*@Data
@Table
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true)
public class Ingredient implements Persistable<String> {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    @Override
    public boolean isNew() {
        return false;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}*/
@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force = true) // Make it Private because Spring JPA requires it so.
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
