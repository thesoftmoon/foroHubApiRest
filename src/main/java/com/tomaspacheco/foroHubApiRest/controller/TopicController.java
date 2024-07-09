package com.tomaspacheco.foroHubApiRest.controller;


import com.tomaspacheco.foroHubApiRest.model.Topic;
import com.tomaspacheco.foroHubApiRest.model.TopicDTO;
import com.tomaspacheco.foroHubApiRest.model.TopicDataListDTO;
import com.tomaspacheco.foroHubApiRest.model.TopicResponseDTO;
import com.tomaspacheco.foroHubApiRest.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    /*@GetMapping
    public String topicsTest(){
        return "test";
    }*/

    @GetMapping
    public ResponseEntity<Page<TopicDataListDTO>> topicList(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicRepository.findAll(paginacion).map(TopicDataListDTO::new));
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> doctorRegister(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriComponentBuilder){
        System.out.println("El request llega correctamente");
        System.out.println(topicDTO);
        Topic topic = topicRepository.save(new Topic(topicDTO));
        //debe ser codigo 201
        //Debes retornar la url donde puedes pillar este doctor
        TopicResponseDTO topicResponseDTO = new TopicResponseDTO(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getStatus(), topic.getAutor(), topic.getCurso());
        /*Crea la url dinamica*/
        URI url = uriComponentBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        /*Retorna la url del doctor creado*/
        return ResponseEntity.created(url).body(topicResponseDTO);
    }

}
