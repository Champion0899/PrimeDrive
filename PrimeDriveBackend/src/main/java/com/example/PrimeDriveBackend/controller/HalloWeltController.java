package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HalloWeltController {

    @GetMapping("/hallo")
    public String hallo() {
        return "Hallo";
    }
}
