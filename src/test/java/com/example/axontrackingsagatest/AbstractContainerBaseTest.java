package com.example.axontrackingsagatest;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

import java.time.Duration;

public abstract class AbstractContainerBaseTest {
    public static final GenericContainer AXONSERVER;

    static {
        AXONSERVER = new GenericContainer("axoniq/axonserver:4.5.1")
                .withExposedPorts(8024, 8124)
                .withStartupTimeout(Duration.ofSeconds(60))
                .waitingFor(new HttpWaitStrategy().forPort(8024));
        AXONSERVER.start();
    }


    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "axon.axonserver.servers=" + AXONSERVER.getContainerIpAddress() + ":" + AXONSERVER.getMappedPort(8124)
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

}
