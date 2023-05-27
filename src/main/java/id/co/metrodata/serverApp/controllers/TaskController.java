package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Task;
import id.co.metrodata.serverApp.models.dto.request.TaskRequest;
import id.co.metrodata.serverApp.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/task")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class TaskController {
    private TaskService taskService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/segment/{id}")
    public List<Task> getBySegment(@PathVariable Long id) {
        return taskService.getBySegmentId(id);
    }
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/trainer")
    public List<Task> getByTrainer() {
        return taskService.getByTrainer();
    }
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/trainee")
    public List<Task> getByTrainee() {
        return taskService.getByTrainee();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_TRAINER')")
    @PostMapping
    public Task create(@RequestBody TaskRequest taskRequest) {
        return taskService.create(taskRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_TRAINER')")
    @PutMapping("/{id}")
    public Task update(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
        return taskService.update(id, taskRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_TRAINER')")
    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}
