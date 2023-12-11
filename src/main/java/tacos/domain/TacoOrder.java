package tacos.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
//import org.springframework.data.annotation.Id; For JDBC
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import tacos.model.Taco;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is a Domain class because it is essential to the Business Logic of what the Web service does
 * @Table: This is optional. By default, the object is mapped to a table based on the domain class name
 * @Table("Taco_Cloud_Order"): I am mapping to another table with a different name
 * @Data Lombok will create the Getters and Setters at Runtime
 * @Id private Long id: This designates the property as the identity for the object.
 * @Column: Explicitly define the column name the property will be mapped to
 *
 * Commented code is used by Spring Data and JDBC. Active Code is Spring JPAs
 */
/*
@Data
@Table("Taco_Cloud_Order")
public class TacoOrder {

    private static final Long serialVersionUID = 1L;

    @Id
    private Long id;

    private Date placedAt;

    @Column("customer_name")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="ZIP is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
 */
@Data
@Entity
public class TacoOrder {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;

    @Column("customer_name")
    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="ZIP is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL) // One order to Many Tacos and if the Order is deleted it will cascade down and delete the tacos within
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
