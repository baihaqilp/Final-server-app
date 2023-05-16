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

import id.co.metrodata.serverApp.models.Submission;
import id.co.metrodata.serverApp.models.dto.request.SubmissionRequest;
import id.co.metrodata.serverApp.services.SubmissionService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/submission")
@AllArgsConstructor
public class SubmissionController {
    private SubmissionService submissionService;

    @GetMapping
    public List<Submission> getAll() {
        return submissionService.getAll();
    }

    @GetMapping("/{id}")
    public Submission getById(@PathVariable Long id) {
        return submissionService.getById(id);
    }

    @PostMapping
    public Submission create(@RequestBody SubmissionRequest submissionRequest) {
        return submissionService.create(submissionRequest);
    }

    @PutMapping("/{id}")
    public Submission update(@PathVariable Long id, @RequestBody SubmissionRequest submissionRequest) {
        return submissionService.update(id, submissionRequest);
    }

    @DeleteMapping("/{id}")
    public Submission delete(@PathVariable Long id) {
        return submissionService.delete(id);
    }
}
