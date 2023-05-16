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

    @GetMapping("/{id}")
    public Classroom getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PostMapping
    public Classroom create(@RequestBody Classroom classroom) {
        return classroomService.create(classroom);
    }

    @PutMapping("/{id}")
    public Classroom update(@PathVariable Long id, @RequestBody Classroom classroom) {
        return classroomService.update(id, classroom);
    }

    @DeleteMapping("/{id}")
    public Classroom delete(@PathVariable Long id) {
        return classroomService.delete(id);
    }
}
