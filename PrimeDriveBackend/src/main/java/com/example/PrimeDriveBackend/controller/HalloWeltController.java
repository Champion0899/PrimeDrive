package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "HalloWelt", description = "Endpoints for testing purposes")
public class HalloWeltController {

    @GetMapping("/hallo")
    @Operation(summary = "Hallo Welt", description = "Returns a simple greeting message.")
    public String hallo() {
        return "Hallo";
    }
}
