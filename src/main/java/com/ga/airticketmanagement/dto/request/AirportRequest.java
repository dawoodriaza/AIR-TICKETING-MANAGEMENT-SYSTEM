package com.ga.airticketmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AirportRequest {

    @NotBlank(message = "Airport name is required")
    @Size(min = 3)
    private String name;

    @NotBlank(message = "Country field is required")
    @Size(min = 3)
    private String country;

    @NotBlank
    @Size(min = 3)
    private String code;
}
