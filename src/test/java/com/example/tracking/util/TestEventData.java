package com.example.tracking.util;

import com.example.tracking.message.DispatchPreparing;

import java.util.UUID;

public class TestEventData {
    public static DispatchPreparing createDispatchPreparingEvent() {
        return DispatchPreparing.builder().orderId(UUID.randomUUID()).build();
    }
}
