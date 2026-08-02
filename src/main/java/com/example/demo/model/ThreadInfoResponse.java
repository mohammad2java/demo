package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response payload for thread information requests")
public class ThreadInfoResponse {

    @Schema(description = "Result message of the operation", example = "Thread information retrieved successfully")
    private String message;

    @Schema(description = "List of running thread information")
    private List<ThreadInfo> data;
}