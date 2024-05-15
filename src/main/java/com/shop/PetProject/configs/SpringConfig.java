package com.shop.PetProject.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(value = "spring.application.scheduling.enabled", havingValue = "true", matchIfMissing = false)
public class SpringConfig {
}
