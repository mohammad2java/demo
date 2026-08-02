package com.example.demo.service;

import com.example.demo.model.ThreatInfo;
import com.example.demo.model.ThreatInfoRequest;
import com.example.demo.model.ThreatInfoResponse;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ThreatInfoService {

    private final Map<Long, ThreatInfo> threatStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ThreatInfoResponse fixThreatNumbers(ThreatInfoRequest request) {
        Long id = idCounter.getAndIncrement();

        ThreatInfo threat = ThreatInfo.builder()
                .id(id)
                .title(request.getTitle())
                .description(request.getDescription())
                .severity(normalizeSeverity(request.getSeverity()))
                .status(normalizeStatus(request.getStatus()))
                .build();

        threatStore.put(id, threat);

        return ThreatInfoResponse.builder()
                .message("Threat information fixed successfully")
                .data(threat)
                .build();
    }

    public ThreatInfoResponse getThreat(Long id) {
        ThreatInfo threat = threatStore.get(id);
        if (threat == null) {
            return ThreatInfoResponse.builder()
                    .message("Threat not found")
                    .data(null)
                    .build();
        }
        return ThreatInfoResponse.builder()
                .message("Threat retrieved successfully")
                .data(threat)
                .build();
    }

    private int normalizeSeverity(int severity) {
        if (severity < 1) {
            return 1;
        }
        if (severity > 10) {
            return 10;
        }
        return severity;
    }

    private String normalizeStatus(String status) {
        if (status == null || status.isBlank()) {
            return "UNKNOWN";
        }
        return status.trim().toUpperCase();
    }

    public com.example.demo.model.ThreadInfoResponse getAllThreadInfo() {
        java.lang.management.ThreadInfo[] jvmThreads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);

        List<com.example.demo.model.ThreadInfo> threads = List.of(jvmThreads).stream()
                .map(thread -> com.example.demo.model.ThreadInfo.builder()
                        .name(thread.getThreadName())
                        .state(thread.getThreadState().name())
                        .daemon(thread.isDaemon())
                        .priority(thread.getPriority())
                        .threadId(thread.getThreadId())
                        .build())
                .collect(Collectors.toList());

        return com.example.demo.model.ThreadInfoResponse.builder()
                .message("Thread information retrieved successfully")
                .data(threads)
                .build();
    }
}