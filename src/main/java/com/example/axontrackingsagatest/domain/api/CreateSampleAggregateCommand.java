package com.example.axontrackingsagatest.domain.api;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateSampleAggregateCommand {
    @TargetAggregateIdentifier
    private final UUID id;
}
