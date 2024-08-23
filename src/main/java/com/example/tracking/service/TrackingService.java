package com.example.tracking.service;

import com.example.tracking.message.DispatchPreparing;
import com.example.tracking.message.Status;
import com.example.tracking.message.TrackingStatusUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingService {

    private static final String TRACKING_STATUS_TOPIC = "tracking.status";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void process(DispatchPreparing message) throws ExecutionException, InterruptedException {
        TrackingStatusUpdated preparingEvent = TrackingStatusUpdated.builder()
                .orderId(message.getOrderId())
                .status(Status.PREPARING)
                .build();
        kafkaTemplate.send(TRACKING_STATUS_TOPIC, preparingEvent).get();
    }
}
