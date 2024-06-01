package pro.angelogomez.ecommerce.backend.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.angelogomez.ecommerce.backend.application.OrderService;
import pro.angelogomez.ecommerce.backend.domain.model.Order;
import pro.angelogomez.ecommerce.backend.domain.model.OrderState;

import java.util.HashMap;
import java.util.Map;

@RestController // http://localhost:8085/api/v1/orders
@RequestMapping("/api/v1/orders")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        log.info("Order received: {}", order);
        if (order.getOrderState().toString().equals(OrderState.CANCELLED.toString()) ) {
            order.setOrderState(OrderState.CANCELLED);
        } else {
            order.setOrderState(OrderState.CONFIRMED);
        }
        return ResponseEntity.ok(orderService.save(order));
    }
    @PostMapping("/update/state/order")
    public ResponseEntity<Map<String, String>> updateStateById(@RequestParam Integer id, @RequestParam String state){
        orderService.updateStateById(id, state);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Order ID: " + id + ", state updated to: " + state);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
    @GetMapping("/by-user/{id}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }
}
