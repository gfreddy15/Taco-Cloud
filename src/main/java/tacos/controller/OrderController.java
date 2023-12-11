package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import javax.validation.Valid;
import tacos.domain.TacoOrder;
import tacos.repo.OrderRepository;

@Slf4j // Logger
@Controller // Component Bean
@RequestMapping("/orders")
@SessionAttributes("tacoOrder") // Keeps Session open with tacoOrder as the object that stays alive
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order Submitted {}", order);
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
