package com.tomaspacheco.foroHubApiRest.model;

import jakarta.validation.constraints.NotBlank;

public record TopicResponseDTO(
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso
) {
}
