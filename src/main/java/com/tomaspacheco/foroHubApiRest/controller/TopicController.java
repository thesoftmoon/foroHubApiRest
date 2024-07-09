package com.tomaspacheco.foroHubApiRest.controller;


import com.tomaspacheco.foroHubApiRest.model.TopicDataListDTO;
import com.tomaspacheco.foroHubApiRest.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<TopicDataListDTO>> doctorList(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicRepository.findAll(paginacion).map(TopicDataListDTO::new));
    }
}
