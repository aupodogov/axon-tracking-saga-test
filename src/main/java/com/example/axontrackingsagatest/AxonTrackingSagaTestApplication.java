package com.example.axontrackingsagatest;

import com.example.axontrackingsagatest.domain.sagas.SampleSaga;
import org.axonframework.config.SagaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AxonTrackingSagaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonTrackingSagaTestApplication.class, args);
    }

    @Bean
    public SagaConfiguration sampleSagaConfiguration() {
        return SagaConfiguration.trackingSagaManager(SampleSaga.class);
    }

    @Bean
    public ListenerErrorHandler listenerErrorHandler() {
        return new ListenerErrorHandler();
    }

}
