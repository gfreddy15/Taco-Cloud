package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tacos.domain.TacoOrder;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.repo.IngredientRepository;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j // Logger
@Controller // Component Bean
@RequestMapping("/design")
@SessionAttributes("tacoOrder") // Keeps Session open with tacoOrder as the object that stays alive
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Model will be passed to the View to display
     * Model Attribute Constructs the Object when the session is
     * instantiated with a GET
     * @param model
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        // Create all the Ingredients we want to display.
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }

    /**
     * Model Attribute constructs the Empty Object to allow
     * the customer to modify with multiple Requests.
     * @return TacoOrder - Empty Object to allow a client to modify per session
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    /**
     * Another Model attribute that will be passed to the view
     * @return Taco - Initially Empty Object
     */
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    /**
     * A GET Method that Instantiates the session and the Model of the TacoOrder
     * @return
     */
    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    /**
     * POST Method to allow a user submit their customized taco and redirect to
     * the other controller
     * @return String - to redirect the user after submitting the form
     */
    @PostMapping
    public String processTaco(
            @Valid Taco taco,
            Errors errors,
            @ModelAttribute TacoOrder tacoOrder){

        if(errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients

                // Stream breaks List into stream for more flexibility while it processes
                .stream()

                /*
                 * Filter works the stream and takes a lambda as parameter
                 * In this case, it checks each Ingredient object x in the stream and keeps
                 * only those where the type of the Ingredient matches the type we provided
                 * as an argument to the filterByType method. In other words, it keeps only
                 * the ingredients with the specified type and filters out the rest.
                 */
                .filter(x -> x.getType().equals(type))

                // Collect data in the stream and puts it back into an object: A list in this case
                .collect(Collectors.toList());
    }
}
