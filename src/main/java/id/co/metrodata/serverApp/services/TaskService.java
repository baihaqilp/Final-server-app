package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Task;
import id.co.metrodata.serverApp.models.dto.request.TaskRequest;
import id.co.metrodata.serverApp.repositories.TaskRepository;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getBySegmentId(Long id) {
        return taskRepository.findBySegment_Id(id);
    }

    public Task getById(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not Found!"));
    }

    public Task create(TaskRequest taskRequest) {
        Task task = modelMapper.map(taskRequest, Task.class);

        task.setSegment(segmentService.getById(taskRequest.getSegmentId()));
        return taskRepository.save(task);
    }

    public Task update(Long id, TaskRequest taskRequest) {
        getById(id);
        Task task = modelMapper.map(taskRequest, Task.class);
        task.setId(id);
        task.setSegment(segmentService.getById(taskRequest.getSegmentId()));
        return taskRepository.save(task);
    }

    public Task delete(Long id) {
        Task task = getById(id);
        taskRepository.delete(task);
        return task;
    }
}
