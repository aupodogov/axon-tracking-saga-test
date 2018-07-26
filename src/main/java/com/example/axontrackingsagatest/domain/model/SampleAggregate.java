package com.example.axontrackingsagatest.domain.model;

import com.example.axontrackingsagatest.domain.api.CreateSampleAggregateCommand;
import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public SampleAggregate(CreateSampleAggregateCommand command) {
        apply(new SampleAggregateCreated(command.getId()));
    }

    @EventSourcingHandler
    public void on(SampleAggregateCreated event) {
        this.id = event.getId();
    }
}
