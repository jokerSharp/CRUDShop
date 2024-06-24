package com.shop.PetProject.controllers;

import com.shop.PetProject.controllers.requests.OrderRequest;
import com.shop.PetProject.controllers.responses.GetOrderResponse;
import com.shop.PetProject.dtos.PageResponse;
import com.shop.PetProject.dtos.customer.CustomerDTO;
import com.shop.PetProject.dtos.customer.CustomerFilter;
import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.dtos.order.OrderFilter;
import com.shop.PetProject.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public PageResponse<OrderDTO> pageableSearch(OrderFilter filter, Pageable pageable) {
        Page<OrderDTO> page = orderService.getAll(filter, pageable);
        PageResponse<OrderDTO> pageResponse = PageResponse.of(page);
        return pageResponse;
    }

    @GetMapping("/search")
    public List<OrderDTO> pageableSearch(@RequestParam String productName) {
        return orderService.getAllByProductName(productName);
    }

    @GetMapping("/{id}")
    public OrderDTO details(@PathVariable long id) {
        return orderService.getOne(id);
    }

    @PostMapping
    public GetOrderResponse save(@RequestBody OrderRequest orderRequest) {
        return orderService.save(orderRequest);
    }

    @PatchMapping("/{id}")
    public GetOrderResponse update(@PathVariable(name = "id") long id, @RequestBody OrderRequest orderRequest) {
        return orderService.update(id, orderRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id) {
        orderService.delete(id);
    }
}
