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

    @GetMapping("/segment/{id}")
    public List<Grade> getBySegment(@PathVariable Long id) {
        return gradeService.getBySegment(id);
    }

    @GetMapping("/classroom/{id}")
    public List<Grade> getByClassroom(@PathVariable Long id) {
        return gradeService.getByClassroom(id);
    }
    @GetMapping("/classroom-trainer/{id}")
    public List<Grade> getByClassroomTrainer(@PathVariable Long id) {
        return gradeService.getByClassroomTrainer(id);
    }
    @GetMapping("/trainee/{id}")
    public List<Grade> getByTraineeId(@PathVariable Long id) {
        return gradeService.getByTraineeId(id);
    }

    @GetMapping("/tes")
    public void testScheduler() {
        gradeService.testScheduler();
    }

    @GetMapping("/{id}")
    public Grade getById(@PathVariable Long id) {
        return gradeService.getById(id);
    }

    @GetMapping("/trainee-passed/{id}")
    public List<Grade> getTraineePassedBySegment(@PathVariable Long id) {
        return gradeService.getGradeTraineePassedBySegment(id);
    }

    @PostMapping
    public Grade create(@RequestBody GradeRequest gradeRequest) {
        return gradeService.create(gradeRequest);
    }

    @DeleteMapping("/{id}")
    public Grade delete(@PathVariable Long id) {
        return gradeService.delete(id);
    }
}
