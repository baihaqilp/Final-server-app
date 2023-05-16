package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Topic;
import id.co.metrodata.serverApp.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {
    private TopicRepository topicRepository;

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public Topic getById(Long id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not Found!"));
    }

    public Topic create(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic update(Topic topic, Long id) {
        getById(id);
        topic.setId(id);
        return topicRepository.save(topic);
    }

    public Topic delete(Long id) {
        Topic topic = getById(id);
        topicRepository.delete(topic);
        return topic;
    }
}
