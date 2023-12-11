package tacos.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.domain.TacoOrder;

import java.util.Date;
import java.util.List;

/**
 * This Repository will be extended to CrudRepository to show the difference between using this interface and the
 * Spring Data JDBC Repository Interface. Additionally, it saves you time from writing the signatures.
 * The other example of Repository will be used in the Ingredient Repository Interface (commented out).
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    //No Longer required due to CRUD
    //TacoOrder save(TacoOrder order);

    /**
     * Spring Data will parse the method signature and know the meaning
     * @Verb find
     * @SignalPropertiesMatch By
     * @Predicate DeliveryZip
     * @Subject TacoOrder. Although it is not specified it will infer it from the Domain classes
     * @param deliveryZip
     * @return
     */
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    /**
     * Spring Data will parse the method signature and know the meaning
     * @Verb read
     * @SignalPropertiesMatch By
     * @Predicate DeliveryZip AND PlacedAt
     * @Operator Between. This signifies that the predicates need to fall between the given values.
     * @Subject Orders. Although specified as Order, it will infer it from the Domain classes as TacoOrder
     * @param
     * @return
     */
    List<TacoOrder> DeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    /**
     * Spring Data will not be able to uhnderstand the following signature, therefore, we use the Spring Data JPA's
     * @Query Annotation. This annotation will take in a custom and more complex method signature.
     */
    @Query("Order o where o.deliveryCity='Seattle'")
    List<TacoOrder> radOrdersDeliveredInSeattle();


}
