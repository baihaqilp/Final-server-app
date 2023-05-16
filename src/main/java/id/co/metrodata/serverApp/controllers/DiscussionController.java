package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Discussion;
import id.co.metrodata.serverApp.models.dto.request.DiscussionRequest;
import id.co.metrodata.serverApp.services.DiscussionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/discussion")
public class DiscussionController {
    private DiscussionService discussionService;
    @GetMapping
    public List<Discussion> getAll() {
        return discussionService.getAll();
    }
    @GetMapping("/{id}")
    public Discussion getById(@PathVariable Long id) {
        return discussionService.getById(id);
    }
    @PostMapping
    public Discussion create(@RequestBody DiscussionRequest discussionRequest) {
        return discussionService.create(discussionRequest);
    }
    @PutMapping("/{id}")
    public Discussion update(@RequestBody DiscussionRequest discussionRequest, @PathVariable Long id) {
        return discussionService.update(discussionRequest, id);
    }
    @DeleteMapping("/{id}")
    public Discussion delete(@PathVariable Long id) {
        return discussionService.delete(id);
    }
}
