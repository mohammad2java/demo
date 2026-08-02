package com.example.demo.controller;

import com.example.demo.model.ThreadInfoResponse;
import com.example.demo.model.ThreatInfoRequest;
import com.example.demo.model.ThreatInfoResponse;
import com.example.demo.service.ThreatInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/threats")
@Tag(name = "Threat Information", description = "API for managing threat information and fixing threat numbers")
public class ThreatInfoController {

    private final ThreatInfoService threatInfoService;

    public ThreatInfoController(ThreatInfoService threatInfoService) {
        this.threatInfoService = threatInfoService;
    }

    @PostMapping
    @Operation(summary = "Fix threat information", description = "Creates a new threat entry with normalized severity and status values")
    @ApiResponse(responseCode = "200", description = "Threat information created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ThreatInfoResponse.class)))
    public ResponseEntity<ThreatInfoResponse> fixThreatInfo(
            @Parameter(description = "Threat information request payload", required = true)
            @RequestBody ThreatInfoRequest request) {
        ThreatInfoResponse response = threatInfoService.fixThreatNumbers(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get threat by ID", description = "Retrieves a threat entry by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Threat found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ThreatInfoResponse.class)))
    @ApiResponse(responseCode = "404", description = "Threat not found")
    public ResponseEntity<ThreatInfoResponse> getThreat(
            @Parameter(description = "Unique ID of the threat", example = "1")
            @PathVariable Long id) {
        ThreatInfoResponse response = threatInfoService.getThreat(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/threads")
    @Operation(summary = "Get all running threads", description = "Fetches information about all threads currently running in the JVM")
    @ApiResponse(responseCode = "200", description = "Thread information retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ThreadInfoResponse.class)))
    public ResponseEntity<ThreadInfoResponse> getAllThreads() {
        ThreadInfoResponse response = threatInfoService.getAllThreadInfo();
        return ResponseEntity.ok(response);
    }
}