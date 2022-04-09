package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.controller.dto.OrderDTO;
import br.com.alelo.consumer.consumerpat.service.OrderService;
import br.com.alelo.consumer.consumerpat.service.UpdateActionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public <T> ResponseEntity<T> buy(@RequestBody OrderDTO orderDTO) {
        var updateActionResponse = orderService.createNew(orderDTO);
        if (UpdateActionResponse.NOT_FOUND.equals(updateActionResponse)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
