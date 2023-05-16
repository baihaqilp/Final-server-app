package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Grade;
import id.co.metrodata.serverApp.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {
    private GradeService gradeService;

    @GetMapping
    public List<Grade> getAll() {
        return gradeService.getAll();
    }

    @GetMapping("/{id}")
    public Grade getById(@PathVariable Long id) {
        return gradeService.getById(id);
    }

    @PostMapping
    public Grade create(@RequestBody Grade grade) {
        return gradeService.create(grade);
    }

    @PutMapping("/{id}")
    public Grade update(@RequestBody Grade grade, @PathVariable Long id) {
        return gradeService.update(id, grade);
    }

    @DeleteMapping("/{id}")
    public Grade delete(@PathVariable Long id) {
        return gradeService.delete(id);
    }
}
