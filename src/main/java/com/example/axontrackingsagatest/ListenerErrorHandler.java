package com.example.axontrackingsagatest;

import org.axonframework.eventhandling.EventListener;
import org.axonframework.eventhandling.EventListenerProxy;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerErrorHandler implements ListenerInvocationErrorHandler {

    private final Logger logger;

    /**
     * Initialize the LoggingErrorHandler using the logger for this class.
     */
    public ListenerErrorHandler() {
        this(LoggerFactory.getLogger(ListenerErrorHandler.class));
    }

    /**
     * Initialize the LoggingErrorHandler to use the given {@code logger} to log errors
     *
     * @param logger the logger to log errors with
     */
    public ListenerErrorHandler(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void onError(Exception exception, EventMessage<?> event, EventListener eventListener) throws Exception {
        Class<?> eventListenerType = eventListener instanceof EventListenerProxy ? ((EventListenerProxy) eventListener).getTargetType() : eventListener.getClass();
        logger.error("EventListener [{}] failed to handle event [{}] ({}). " +
                        "Starting retry mode",
                eventListenerType.getSimpleName(),
                event.getIdentifier(),
                event.getPayloadType().getName(),
                exception);
        throw exception;
    }
}
