package com.bibliotex.catalog.infra;

import com.bibliotex.catalog.services.KafkaService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class KafkaServiceConfiguration {

    @Bean
    @Primary
    public KafkaService kafkaService() {
        return Mockito.mock(KafkaService.class);
    }
}
