package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Topic;
import id.co.metrodata.serverApp.models.dto.request.TopicRequest;
import id.co.metrodata.serverApp.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {
    private TopicRepository topicRepository;
    private ModelMapper modelMapper;
    private ProgramService programService;

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    public Topic getById(Long id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topic not Found!"));
    }

    public Topic create(TopicRequest topicRequest) {
        Topic topic = modelMapper.map(topicRequest, Topic.class);
        topic.setProgram(programService.getById(topicRequest.getProgram_id()));
        return topicRepository.save(topic);
    }

    public Topic update(TopicRequest topicRequest, Long id) {
        getById(id);
        Topic topic = modelMapper.map(topicRequest, Topic.class);
        topic.setProgram(programService.getById(topicRequest.getProgram_id()));
        topic.setId(id);
        return topicRepository.save(topic);
    }

    public Topic delete(Long id) {
        Topic topic = getById(id);
        topicRepository.delete(topic);
        return topic;
    }
}
