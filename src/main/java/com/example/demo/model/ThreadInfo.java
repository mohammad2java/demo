package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about a running thread")
public class ThreadInfo {

    @Schema(description = "Name of the thread", example = "main")
    private String name;

    @Schema(description = "Thread state", example = "RUNNABLE")
    private String state;

    @Schema(description = "Whether the thread is a daemon thread", example = "false")
    private boolean daemon;

    @Schema(description = "Thread priority", example = "5")
    private int priority;

    @Schema(description = "Unique thread ID", example = "1")
    private long threadId;
}