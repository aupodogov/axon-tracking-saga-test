package com.example.axontrackingsagatest.domain.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.snapshotting.SnapshotFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public SnapshotTriggerDefinition sampleSnapshotTrigger(
            @Value("${axon.snapshot-trigger.threshold:20}") int threshold,
            Snapshotter snapshotter
    ) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

    @Bean
    public SnapshotFilter sampleSnapshotFilter() {
        return snapshotData -> "1.0".equals(snapshotData.getPayload().getType().getRevision());
    }

    @Bean
    public SnapshotTriggerDefinition otherSnapshotTrigger(
            @Value("${axon.snapshot-trigger.threshold:20}") int threshold,
            Snapshotter snapshotter
    ) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

    @Bean
    public SnapshotFilter otherSnapshotFilter() {
        return snapshotData -> "1.1".equals(snapshotData.getPayload().getType().getRevision());
    }

}
