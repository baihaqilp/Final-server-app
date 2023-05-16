package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Task;
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
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }
    @PutMapping("/{id}")
    public Task update(@RequestBody Task task, @PathVariable Long id) {
        return taskService.update(id, task);
    }
    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
