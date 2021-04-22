package com.example.axontrackingsagatest.domain.api;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class SampleServiceCommand {
    @TargetAggregateIdentifier
    private final UUID id;
}
