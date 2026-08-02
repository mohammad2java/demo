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
@Schema(description = "Response payload for threat information operations")
public class ThreatInfoResponse {

    @Schema(description = "Result message of the operation", example = "Threat information fixed successfully")
    private String message;

    @Schema(description = "Threat information data")
    private ThreatInfo data;
}