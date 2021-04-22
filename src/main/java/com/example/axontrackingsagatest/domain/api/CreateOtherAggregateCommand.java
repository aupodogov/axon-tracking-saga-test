package com.example.axontrackingsagatest.domain.api;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateOtherAggregateCommand {
    @TargetAggregateIdentifier
    private final UUID id;
}
