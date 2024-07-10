package com.tomaspacheco.foroHubApiRest.model.topic;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TopicDataListDTO(
        Long id,
        String titulo,
        /* Formateador de hora para el json */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fecha_creacion,
        String mensaje,
        String  autor,
        String curso) {

    public TopicDataListDTO(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getFecha_creacion(), topic.getMensaje(), topic.getAutor(), topic.getCurso());
    }
}
