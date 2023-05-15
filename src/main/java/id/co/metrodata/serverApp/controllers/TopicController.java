package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Topic;
import id.co.metrodata.serverApp.services.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/topic")
public class TopicController {
    private TopicService topicService;
    @GetMapping
    public List<Topic> getAll() {
        return topicService.getAll();
    }
    @GetMapping("/{id}")
    public Topic getById(@PathVariable Long id) {
        return topicService.getById(id);
    }
    @PostMapping
    public Topic create(@RequestBody Topic topic) {
        return topicService.create(topic);
    }
    @PutMapping("/{id}")
    public Topic update(@RequestBody Topic topic, @PathVariable Long id) {
        return topicService.update(topic, id);
    }
    @DeleteMapping("/{id}")
    public Topic delete(@PathVariable Long id) {
        return topicService.delete(id);
    }
}
