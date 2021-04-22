package com.example.axontrackingsagatest.domain.api;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class ModifySampleAggregateCommand {
    @TargetAggregateIdentifier
    private final UUID id;
}
