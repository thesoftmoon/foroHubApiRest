package com.tomaspacheco.foroHubApiRest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion;
    private String status;
    private String autor;
    private String curso;

    @PrePersist
    protected void onCreate() {
        this.fecha_creacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /*Constructor*/

    public Topic() {

    }

    public Topic(TopicDTO topicDTO) {
        this.titulo = topicDTO.titulo();
        this.mensaje = topicDTO.mensaje();
        this.status = topicDTO.status();
        this.autor = topicDTO.autor();
        this.curso = topicDTO.curso();
    }

    /*Updater*/
    public void updateData(TopicDataUpdateDTO topicDataUpdateDTO) {

        if (topicDataUpdateDTO.titulo() != null) {
            this.titulo = topicDataUpdateDTO.titulo();
        }
        if (topicDataUpdateDTO.mensaje() != null) {
            this.mensaje = topicDataUpdateDTO.mensaje();
        }
        if (topicDataUpdateDTO.mensaje() != null) {
            this.status = topicDataUpdateDTO.status();
        }
        if (topicDataUpdateDTO.mensaje() != null) {
            this.autor = topicDataUpdateDTO.autor();
        }
        if (topicDataUpdateDTO.mensaje() != null) {
            this.curso = topicDataUpdateDTO.curso();
        }
    }
}
