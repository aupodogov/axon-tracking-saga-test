package com.example.axontrackingsagatest;

import com.example.axontrackingsagatest.domain.api.CreateSampleAggregateCommand;
import com.example.axontrackingsagatest.domain.service.SampleService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AxonTrackingSagaTestApplicationTests {
    @MockBean
    private SampleService sampleService;
    @Autowired
    private CommandGateway commandGateway;

	@Test
	public void testSaga() throws Exception {
	    when(sampleService.someMethod())
                .thenThrow(new Exception())
                .thenThrow(new IllegalStateException())
                .thenReturn("Ok");
		commandGateway.sendAndWait(new CreateSampleAggregateCommand(UUID.randomUUID()));

		verify(sampleService, timeout(10000).times(3)).someMethod();
	}

}
