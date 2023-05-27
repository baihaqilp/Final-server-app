package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Evaluation;
import id.co.metrodata.serverApp.models.dto.request.EvaluationRequest;
import id.co.metrodata.serverApp.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/evaluation")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class EvaluationController {
    private EvaluationService evaluationService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Evaluation> getAll() {
        return evaluationService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Evaluation getById(@PathVariable Long id) {
        return evaluationService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/task/{id}")
    public List<Evaluation> getByTask(@PathVariable Long id) {
        return evaluationService.getByTask(id);
    }

    @PreAuthorize("hasAuthority('CREATE_TRAINER')")
    @PostMapping
    public Evaluation create(@RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.create(evaluationRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_TRAINER')")
    @PutMapping("/{id}")
    public Evaluation update(@PathVariable Long id, @RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.update(id, evaluationRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_TRAINER')")
    @DeleteMapping("/{id}")
    public Evaluation delete(@PathVariable Long id) {
        return evaluationService.delete(id);
    }
}
