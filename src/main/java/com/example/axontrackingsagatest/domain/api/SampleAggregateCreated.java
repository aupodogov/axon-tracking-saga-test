package com.example.axontrackingsagatest.domain.api;

import lombok.Value;

import java.util.UUID;

@Value
public class SampleAggregateCreated {
    private final UUID id;
}
