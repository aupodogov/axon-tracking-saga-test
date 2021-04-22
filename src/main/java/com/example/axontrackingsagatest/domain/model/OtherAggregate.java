package com.example.axontrackingsagatest.domain.model;

import com.example.axontrackingsagatest.domain.api.CreateOtherAggregateCommand;
import com.example.axontrackingsagatest.domain.api.OtherAggregateCreated;
import com.example.axontrackingsagatest.domain.api.SampleAggregateCreated;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.serialization.Revision;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Revision("1.1")
@Aggregate(snapshotTriggerDefinition = "otherSnapshotTrigger", snapshotFilter = "otherSnapshotFilter", cache = "aggregateCache")
@NoArgsConstructor
public class OtherAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public OtherAggregate(CreateOtherAggregateCommand command) {
        apply(new SampleAggregateCreated(command.getId()));
    }

    @EventSourcingHandler
    public void on(OtherAggregateCreated event) {
        this.id = event.getId();
    }
}
