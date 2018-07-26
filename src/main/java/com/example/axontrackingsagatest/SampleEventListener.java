package com.example.axontrackingsagatest;

import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import com.example.axontrackingsagatest.domain.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@XSlf4j
@Component
@RequiredArgsConstructor
@ProcessingGroup("EventProcessor")
public class SampleEventListener {
    private final SampleService sampleService;

    @EventHandler
    public void on(SampleAggregateCreated event) throws Exception {
        log.entry(event);
        sampleService.otherMethod();
    }
}
