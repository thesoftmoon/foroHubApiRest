package com.tomaspacheco.foroHubApiRest.repository;

import com.tomaspacheco.foroHubApiRest.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
