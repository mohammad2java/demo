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
@Schema(description = "Threat information entity")
public class ThreatInfo {

    @Schema(description = "Unique identifier of the threat", example = "1")
    private Long id;

    @Schema(description = "Title of the threat", example = "SQL Injection Detected")
    private String title;

    @Schema(description = "Detailed description of the threat", example = "Potential SQL injection in user input field")
    private String description;

    @Schema(description = "Severity level of the threat (1-10)", example = "8")
    private int severity;

    @Schema(description = "Current status of the threat", example = "OPEN")
    private String status;
}