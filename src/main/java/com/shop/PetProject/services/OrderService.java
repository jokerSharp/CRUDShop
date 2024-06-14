package com.shop.PetProject.services;

import com.querydsl.core.types.Predicate;
import com.shop.PetProject.controllers.requests.OrderRequest;
import com.shop.PetProject.controllers.requests.OrderedProductInfo;
import com.shop.PetProject.controllers.responses.GetOrderResponse;
import com.shop.PetProject.dtos.QPredicates;
import com.shop.PetProject.dtos.order.OrderDTO;
import com.shop.PetProject.dtos.order.OrderFilter;
import com.shop.PetProject.dtos.order.OrderTotalDTO;
import com.shop.PetProject.exceptions.customer.CustomerNotFoundException;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.shop.PetProject.models.QOrderEntity.orderEntity;
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
                    .findFirst().orElseThrow(() -> new ProductNotFoundException("Product is not found"));
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
    public OrderDTO update(long id, OrderDTO orderDTO) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity orderEntityToUpdate = conversionService.convert(orderDTO, OrderEntity.class);
            orderEntityToUpdate.setId(id);
            Optional<CustomerEntity> customerEntity = customerRepository.findById(orderDTO.customerId());
            customerEntity.ifPresent(orderEntityToUpdate::setCustomer);
            OrderEntity updatedOrderEntity = orderRepository.save(orderEntityToUpdate);
            return conversionService.convert(updatedOrderEntity, OrderDTO.class);
        } else {
            throw new OrderNotFoundException("Order is not found");
        }
    }

    @Transactional
    public void delete(long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order is not found");
        }
    }

}
