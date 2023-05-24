package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Task;
import id.co.metrodata.serverApp.models.dto.request.TaskRequest;
import id.co.metrodata.serverApp.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/segment/{id}")
    public List<Task> getBySegment(@PathVariable Long id) {
        return taskService.getBySegmentId(id);
    }
    @GetMapping("/trainer")
    public List<Task> getByTrainer() {
        return taskService.getByTrainer();
    }
    @GetMapping("/trainee")
    public List<Task> getByTrainee() {
        return taskService.getByTrainee();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    public Task create(@RequestBody TaskRequest taskRequest) {
        return taskService.create(taskRequest);
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
