package com.example.axontrackingsagatest;

import com.example.axontrackingsagatest.domain.api.CreateSampleAggregateCommand;
import com.example.axontrackingsagatest.domain.api.ModifySampleAggregateCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {AbstractContainerBaseTest.Initializer.class})
public class AxonTrackingSagaTestApplicationTests extends AbstractContainerBaseTest {
    @Autowired
    private CommandGateway commandGateway;

    @Test
    void testSnapshotting() throws InterruptedException {
        final UUID id = UUID.randomUUID();
        commandGateway.sendAndWait(new CreateSampleAggregateCommand(id));
        for (int i = 0; i < 100; i++) {
            commandGateway.sendAndWait(new ModifySampleAggregateCommand(id));
        }
        Thread.sleep(30000);
    }
}
