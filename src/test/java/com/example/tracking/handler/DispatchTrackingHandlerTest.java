package com.example.tracking.handler;

import com.example.tracking.message.DispatchPreparing;
import com.example.tracking.service.TrackingService;
import com.example.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DispatchTrackingHandlerTest {

    @InjectMocks
    private DispatchTrackingHandler dispatchTrackingHandler;

    @Mock
    private TrackingService trackingService;

    @Test
    void listen() {
        DispatchPreparing message = TestEventData.createDispatchPreparingEvent();
        dispatchTrackingHandler.listen(message);
        Mockito.verify(trackingService, Mockito.times(1)).process(message);
    }
}