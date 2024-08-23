package com.example.tracking.service;

import com.example.tracking.message.DispatchPreparing;
import com.example.tracking.message.TrackingStatusUpdated;
import com.example.tracking.util.TestEventData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackingServiceTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private TrackingService trackingService;

    @Test
    void process_Success() throws Exception{
        when(kafkaTemplate.send(anyString(), any(TrackingStatusUpdated.class))).thenReturn(mock(CompletableFuture.class));

        DispatchPreparing event = TestEventData.createDispatchPreparingEvent();
        trackingService.process(event);

        verify(kafkaTemplate, times(1)).send(eq("tracking.status"), any(TrackingStatusUpdated.class));
    }

    @Test
    void process_ThrowsException() {
        DispatchPreparing event = TestEventData.createDispatchPreparingEvent();
        when(kafkaTemplate.send(anyString(), any(TrackingStatusUpdated.class))).thenThrow(new RuntimeException());

        Assertions.assertThrows(RuntimeException.class, () -> trackingService.process(event));

        verify(kafkaTemplate, times(1)).send(eq("tracking.status"), any(TrackingStatusUpdated.class));
        verifyNoMoreInteractions(kafkaTemplate);
    }
}