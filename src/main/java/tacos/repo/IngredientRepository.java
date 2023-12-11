package tacos.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import tacos.model.Ingredient;

//public interface IngredientRepository extends Repository<Ingredient, String> {
//
//    // Queries for all Ingredients
//    Iterable<Ingredient> findAll();
//
//    // Queries for a specific Ingredient by ID
//    Optional<Ingredient> findById(String id);
//
//    // Persists the Ingredient
//    Ingredient save(Ingredient ingredient);
//}

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
