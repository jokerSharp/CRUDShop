package com.shop.PetProject.services;

import com.shop.PetProject.repositories.ProductRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {
    private final ProductRepository productRepository;

    public ScheduleService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(fixedDelayString = "${spring.application.scheduling.period}")
    @Transactional
    public void updatePrice() {
        productRepository.updatePrice();
    }
}
