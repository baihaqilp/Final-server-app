package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Comment;
import id.co.metrodata.serverApp.models.dto.CommentRequest;
import id.co.metrodata.serverApp.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public Comment getById(@PathVariable Long id) {
        return commentService.getById(id);
    }

    @PostMapping
    public Comment create(@RequestBody CommentRequest commentRequest) {
        return commentService.create(commentRequest);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        return commentService.update(id, commentRequest);
    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
}
