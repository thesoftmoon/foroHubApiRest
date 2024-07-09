package com.tomaspacheco.foroHubApiRest.model;

import jakarta.validation.constraints.NotNull;

public record TopicDataUpdateDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String status,
        String autor,
        String curso
) {
}
