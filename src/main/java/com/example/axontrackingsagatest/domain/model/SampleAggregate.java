package com.example.axontrackingsagatest.domain.model;

import com.example.axontrackingsagatest.domain.api.CreateSampleAggregateCommand;
import com.example.axontrackingsagatest.domain.api.ModifySampleAggregateCommand;
import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import com.example.axontrackingsagatest.domain.api.SampleAggregateModified;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Revision("1.0")
@Aggregate(snapshotTriggerDefinition = "sampleSnapshotTrigger", snapshotFilter = "sampleSnapshotFilter")
@NoArgsConstructor
public class SampleAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public SampleAggregate(CreateSampleAggregateCommand command) {
        apply(new SampleAggregateCreated(command.getId()));
    }

    @CommandHandler
    public void handle(ModifySampleAggregateCommand command) {
        apply(new SampleAggregateModified(command.getId()));
    }

    @EventSourcingHandler
    public void on(SampleAggregateCreated event) {
        this.id = event.getId();
    }
}
