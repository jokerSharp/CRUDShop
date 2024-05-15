package com.shop.PetProject.services;

import com.shop.PetProject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ProductRepository productRepository;

    @Scheduled(fixedDelayString = "${spring.application.scheduling.period}")
    @Transactional
    public void updatePrice() {
        productRepository.updatePrice();
    }
}
