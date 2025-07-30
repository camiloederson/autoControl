package com.mikadev.autocontrolapi.vehicleBrand;

import jakarta.validation.constraints.NotBlank;

public record VehicleBrandPostDTO (
        @NotBlank(message = "Brand name is required") String name) {
}
