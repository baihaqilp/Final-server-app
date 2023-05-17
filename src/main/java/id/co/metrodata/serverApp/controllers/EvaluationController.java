package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Evaluation;
import id.co.metrodata.serverApp.models.dto.request.EvaluationRequest;
import id.co.metrodata.serverApp.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    private EvaluationService evaluationService;
    @GetMapping
    public List<Evaluation> getAll() {
        return evaluationService.getAll();
    }

    @GetMapping("/{id}")
    public Evaluation getById(@PathVariable Long id) {
        return evaluationService.getById(id);
    }

    @PostMapping
    public Evaluation create(@RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.create(evaluationRequest);
    }

    @PutMapping("/{id}")
    public Evaluation update(@PathVariable Long id, @RequestBody EvaluationRequest evaluationRequest) {
        return evaluationService.update(id, evaluationRequest);
    }

    @DeleteMapping("/{id}")
    public Evaluation delete(@PathVariable Long id) {
        return evaluationService.delete(id);
    }
}
