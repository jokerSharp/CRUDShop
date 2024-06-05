package com.shop.PetProject.controllers;

import com.shop.PetProject.dtos.OrderDTO;
import com.shop.PetProject.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDTO details(@PathVariable long id) {
        return orderService.getOne(id);
    }

    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable(name = "id") long id, @RequestBody OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        orderService.delete(id);
    }
}
