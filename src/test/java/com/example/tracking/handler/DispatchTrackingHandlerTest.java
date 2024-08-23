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

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DispatchTrackingHandlerTest {

    @InjectMocks
    private DispatchTrackingHandler dispatchTrackingHandler;

    @Mock
    private TrackingService trackingService;

    @Test
    void listen_Success() throws Exception {
        DispatchPreparing event = TestEventData.createDispatchPreparingEvent();
        dispatchTrackingHandler.listen(event);
        Mockito.verify(trackingService, Mockito.times(1)).process(event);
    }

}