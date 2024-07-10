package com.tomaspacheco.foroHubApiRest.controller;


import com.tomaspacheco.foroHubApiRest.model.*;
import com.tomaspacheco.foroHubApiRest.repository.TopicRepository;
import jakarta.transaction.Transactional;
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
    public ResponseEntity<Page<TopicDataListDTO>> topicList(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicRepository.findAll(paginacion).map(TopicDataListDTO::new));
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> doctorRegister(@RequestBody @Valid TopicDTO topicDTO, UriComponentsBuilder uriComponentBuilder) {
        Topic topic = topicRepository.save(new Topic(topicDTO));
        TopicResponseDTO topicResponseDTO = new TopicResponseDTO(topic.getId(), topic.getTitulo(), topic.getFecha_creacion(), topic.getMensaje(), topic.getStatus(), topic.getAutor(), topic.getCurso());
        URI url = uriComponentBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity doctorUpdate(@RequestBody @Valid TopicDataUpdateDTO topicDataUpdateDTO) {
        Topic topic = topicRepository.getReferenceById(topicDataUpdateDTO.id());
        topic.updateData(topicDataUpdateDTO);
        return ResponseEntity.ok(
                new TopicResponseDTO(topic.getId(), topic.getTitulo(), topic.getFecha_creacion(), topic.getMensaje(), topic.getStatus(), topic.getAutor(), topic.getCurso())
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
    }

}
