package com.example.tracking.handler;

import com.example.tracking.message.DispatchPreparing;
import com.example.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DispatchTrackingHandler {

    private final TrackingService trackingService;

    @KafkaListener(
            id = "dispatchTrackingConsumer",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.tracking.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing message) {
        log.info("Message received with payload: " + message);
        trackingService.process(message);
    }
}
