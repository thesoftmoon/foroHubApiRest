package com.tomaspacheco.foroHubApiRest.model;

public record TopicDataListDTO(Long id, String titulo, String mensaje, String  autor, String curso) {

    public TopicDataListDTO(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getAutor(), topic.getCurso());
    }
}
