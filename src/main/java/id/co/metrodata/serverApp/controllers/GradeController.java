package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Grade;
import id.co.metrodata.serverApp.models.dto.request.GradeRequest;
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
    public Grade create(@RequestBody GradeRequest gradeRequest) {
        return gradeService.create(gradeRequest);
    }

    @PutMapping("/{id}")
    public Grade update(@RequestBody GradeRequest gradeRequest, @PathVariable Long id) {
        return gradeService.update(id, gradeRequest);
    }

    @DeleteMapping("/{id}")
    public Grade delete(@PathVariable Long id) {
        return gradeService.delete(id);
    }
}
