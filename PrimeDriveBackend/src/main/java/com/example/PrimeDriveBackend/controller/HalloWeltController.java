
/**
 * HalloWeltController.java
 * 
 * This controller provides a simple test endpoint that returns a greeting message.
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 */
package com.example.PrimeDriveBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * HalloWeltController.java
 * 
 * @author Fatlum Epiroti
 * @version 1.0
 * @since 2025-06-03
 * 
 *        This controller is part of the API documentation and provides a simple
 *        test endpoint
 *        that returns a greeting message.
 */
@RestController
@Tag(name = "Hello World Controller", description = "This controller provides a simple test endpoint that returns a greeting message.")
public class HalloWeltController {

    @GetMapping("/hallo")
    @Operation(summary = "Hallo Welt", description = "Returns a simple greeting message.")
    public String hallo() {
        return "Hallo";
    }
}
