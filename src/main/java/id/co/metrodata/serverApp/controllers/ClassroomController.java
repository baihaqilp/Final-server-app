package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.services.ClassroomService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/classroom")
public class ClassroomController {
    private ClassroomService classroomService;

    @GetMapping
    public List<Classroom> getAll() {
        return classroomService.getAll();
    }

    @GetMapping("/program/{id}")
    public List<Classroom> getByProgram(@PathVariable Long id) {
        return classroomService.getByProgram(id);
    }

    @GetMapping("/{id}")
    public Classroom getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @GetMapping("/trainee")
    public Classroom getByTrainee() {
        return classroomService.getByTraine();
    }

    @PostMapping
    public Classroom create(@RequestBody ClassroomRequest classroomRequest) {
        return classroomService.create(classroomRequest);
    }

    @PutMapping("/{id}")
    public Classroom update(@PathVariable Long id, @RequestBody ClassroomRequest classroomRequest) {
        return classroomService.update(id, classroomRequest);
    }

    @DeleteMapping("/{id}")
    public Classroom delete(@PathVariable Long id) {
        return classroomService.delete(id);
    }
}
