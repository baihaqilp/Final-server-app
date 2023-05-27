package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Topic;
import id.co.metrodata.serverApp.models.dto.request.TopicRequest;
import id.co.metrodata.serverApp.services.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/topic")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class TopicController {
    private TopicService topicService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Topic> getAll() {
        return topicService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Topic getById(@PathVariable Long id) {
        return topicService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_TRAINER')")
    @PostMapping
    public Topic create(@RequestBody TopicRequest topicRequest) {
        return topicService.create(topicRequest);
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_TRAINER')")
    @PutMapping("/{id}")
    public Topic update(@RequestBody TopicRequest topicRequest, @PathVariable Long id) {
        return topicService.update(topicRequest, id);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_TRAINER')")
    @DeleteMapping("/{id}")
    public Topic delete(@PathVariable Long id) {
        return topicService.delete(id);
    }
}
