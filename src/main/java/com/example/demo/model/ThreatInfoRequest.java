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
@Schema(description = "Request payload for creating or updating threat information")
public class ThreatInfoRequest {

    @Schema(description = "Title of the threat", example = "XSS Vulnerability")
    private String title;

    @Schema(description = "Detailed description of the threat", example = "Reflected XSS in search parameter")
    private String description;

    @Schema(description = "Severity level of the threat (1-10)", example = "5")
    private int severity;

    @Schema(description = "Current status of the threat", example = "OPEN")
    private String status;
}