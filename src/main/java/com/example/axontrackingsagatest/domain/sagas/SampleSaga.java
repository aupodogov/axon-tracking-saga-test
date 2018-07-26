package com.example.axontrackingsagatest.domain.sagas;

import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import com.example.axontrackingsagatest.domain.service.SampleService;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import static org.axonframework.eventhandling.saga.SagaLifecycle.end;

@XSlf4j
@Saga
public class SampleSaga {
    private transient CommandGateway commandGateway;
    private transient SampleService sampleService;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void on(SampleAggregateCreated event) throws Exception {
        log.entry(event);
        sampleService.someMethod();
        end();
    }


    @Autowired
    public void setSampleService(SampleService sampleService) {
        this.sampleService = sampleService;
    }
}
