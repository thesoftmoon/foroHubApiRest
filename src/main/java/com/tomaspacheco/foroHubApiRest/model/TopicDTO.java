package com.tomaspacheco.foroHubApiRest.model;

import jakarta.validation.constraints.NotBlank;

public record TopicDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String status,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
