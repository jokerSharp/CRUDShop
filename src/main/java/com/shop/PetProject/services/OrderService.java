package com.shop.PetProject.services;

import com.querydsl.core.types.Predicate;
import com.shop.PetProject.controllers.requests.OrderRequest;
import com.shop.PetProject.controllers.requests.OrderedProductInfo;
import com.shop.PetProject.controllers.responses.GetOrderResponse;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.dtos.order.OrderFilter;
import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
import com.shop.PetProject.exceptions.order.OrderIsNotCreatedException;
import com.shop.PetProject.exceptions.order.OrderNotFoundException;
import com.shop.PetProject.exceptions.product.ProductNotFoundException;
import com.shop.PetProject.models.*;
import com.shop.PetProject.repositories.customer.CustomerRepository;
import com.shop.PetProject.repositories.order.OrderRepository;
import com.shop.PetProject.repositories.order.OrderTotalRepository;
import com.shop.PetProject.repositories.product.ProductRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.shop.PetProject.models.QOrderEntity.orderEntity;
import static com.shop.PetProject.models.QOrderTotal.orderTotal;
import static com.shop.PetProject.utils.builders.OrderTotalKeyBuilder.getOrderTotalKey;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderTotalRepository orderTotalRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ConversionService conversionService;

    public OrderService(OrderRepository orderRepository, OrderTotalRepository orderTotalRepository, ProductRepository productRepository, CustomerRepository customerRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.orderTotalRepository = orderTotalRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.conversionService = conversionService;
    }

    public Page<OrderDTO> getAll(OrderFilter filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder()
                .add(filter.id(), orderEntity.id::eq)
                .add(filter.status(), orderEntity.status::eq)
                .build();
        return orderRepository.findAll(predicate, pageable)
                .map(order -> conversionService.convert(order, OrderDTO.class));
    }

    public List<OrderDTO> getAllByProductName(String productName) {
        orderRepository.findByProductName(productName).forEach(order -> conversionService.convert(order, OrderDTO.class));
        return orderRepository.findByProductName(productName).stream()
                .map(order -> conversionService.convert(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO getOne(long id) {
        return conversionService.convert(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order is not found")), OrderDTO.class);
    }

    @Transactional
    public GetOrderResponse save(OrderRequest orderRequest) {
        CustomerEntity customerEntity = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer is not found"));

        OrderEntity orderEntity = OrderEntity.builder()
                .customer(customerEntity)
                .totalPrice(BigDecimal.ZERO)
                .status(OrderStatuses.CREATED)
                .build();
        orderEntity = orderRepository.save(orderEntity);

        for (OrderedProductInfo orderedProductInfo : orderRequest.orderedProductInfo()) {
            ProductEntity productEntity = productRepository.findByName(orderedProductInfo.productName()).stream()
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException("Product is not found"));

            if (productEntity.getQuantity() >= orderedProductInfo.quantity()) {
                productEntity.setQuantity(productEntity.getQuantity() - orderedProductInfo.quantity());
            } else {
                throw new OrderIsNotCreatedException(
                        "Order is not created. Required %s quantity is: %d".formatted(
                                orderedProductInfo.productName(),
                                (orderedProductInfo.quantity() - productEntity.getQuantity())
                        )
                );
            }

            OrderTotal orderTotal = OrderTotal.builder()
                    .id(getOrderTotalKey(orderEntity, productEntity))
                    .orderEntity(orderEntity)
                    .product(productEntity)
                    .quantity(orderedProductInfo.quantity())
                    .subtotal(BigDecimal.valueOf(productEntity.getPrice() * orderedProductInfo.quantity()))
                    .build();
            orderTotalRepository.save(orderTotal);
            orderEntity.setTotalPrice(orderEntity.getTotalPrice().add(orderTotal.getSubtotal()));
        }

        return conversionService.convert(orderEntity, GetOrderResponse.class);
    }

    @Transactional
    public GetOrderResponse update(long id, OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order is not found"));

        for (OrderedProductInfo orderedProductInfo : orderRequest.orderedProductInfo()) {
            ProductEntity productEntity = productRepository.findByName(orderedProductInfo.productName()).stream()
                    .findFirst()
                    .orElseThrow(() -> new ProductNotFoundException("Product is not found"));

            if (productEntity.getQuantity() >= orderedProductInfo.quantity()) {
                productEntity.setQuantity(productEntity.getQuantity() - orderedProductInfo.quantity());
            } else {
                throw new OrderIsNotCreatedException(
                        "Order is not updated. Required %s quantity is: %d".formatted(
                                orderedProductInfo.productName(),
                                (orderedProductInfo.quantity() - productEntity.getQuantity())
                        )
                );
            }

            OrderTotalKey key = getOrderTotalKey(orderEntity, productEntity);
            Optional<OrderTotal> optionalOrderTotal = orderTotalRepository.findById(key);
            OrderTotal orderTotal;

            if (optionalOrderTotal.isPresent()) {
                orderTotal = optionalOrderTotal.get();
                orderTotal.setQuantity(orderTotal.getQuantity() + orderedProductInfo.quantity());
                orderTotal.setSubtotal(BigDecimal.valueOf(productEntity.getPrice() * orderedProductInfo.quantity()));
            } else {
                orderTotal = OrderTotal.builder()
                        .id(getOrderTotalKey(orderEntity, productEntity))
                        .orderEntity(orderEntity)
                        .product(productEntity)
                        .quantity(orderedProductInfo.quantity())
                        .subtotal(BigDecimal.valueOf(productEntity.getPrice() * orderedProductInfo.quantity()))
                        .build();
            }

            orderTotalRepository.save(orderTotal);
            orderEntity.setTotalPrice(orderEntity.getTotalPrice().add(orderTotal.getSubtotal()));
        }

        return conversionService.convert(orderEntity, GetOrderResponse.class);
    }

    @Transactional
    public void delete(long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order is not found"));
        if (orderEntity.getStatus().equals(OrderStatuses.CANCELLED)) {
            throw new OrderNotFoundException("Order is already cancelled");
        }

        Set<OrderTotal> orderTotalSet = orderTotalRepository.findAll().stream()
                .filter(order -> order.getId().getOrderId().equals(id))
                .collect(Collectors.toSet());

        for (OrderTotal orderTotal : orderTotalSet) {
            ProductEntity product = productRepository.findByName(orderTotal.getProduct().getName()).stream()
                    .findFirst()
                    .get();
            product.setQuantity(product.getQuantity() + orderTotal.getQuantity());
            orderTotalRepository.deleteById(orderTotal.getId());
        }

        orderEntity.setStatus(OrderStatuses.CANCELLED);
    }

}
