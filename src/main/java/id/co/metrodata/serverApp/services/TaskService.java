package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Task;
import id.co.metrodata.serverApp.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not Found!"));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        getById(id);
        task.setId(id);
        return taskRepository.save(task);
    }

    public Task delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
        return task;
    }
}
