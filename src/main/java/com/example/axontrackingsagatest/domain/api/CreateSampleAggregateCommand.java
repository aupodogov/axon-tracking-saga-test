package com.example.axontrackingsagatest.domain.api;

import com.sun.istack.internal.NotNull;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class CreateSampleAggregateCommand {
    @NotNull
    @TargetAggregateIdentifier
    private final UUID id;
}
