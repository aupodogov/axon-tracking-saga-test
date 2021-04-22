package com.example.axontrackingsagatest.domain.model;

import com.example.axontrackingsagatest.domain.api.CreateThirdAggregateCommand;
import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import com.example.axontrackingsagatest.domain.api.ThirdAggregateCreated;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class ThirdAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public ThirdAggregate(CreateThirdAggregateCommand command) {
        apply(new SampleAggregateCreated(command.getId()));
    }

    @EventSourcingHandler
    public void on(ThirdAggregateCreated event) {
        this.id = event.getId();
    }
}
