package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class SubmissionController {
    private SubmissionService submissionService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Submission> getAll() {
        return submissionService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/task/{id}")
    public List<Submission> getByTask(@PathVariable Long id) {
        return submissionService.getByTask(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Submission getById(@PathVariable Long id) {
        return submissionService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/task/{id}/trainee")
    public Submission getByTaskTrainee(@PathVariable Long id) {
        return submissionService.getByTaskTrainee(id);
    }

    @PreAuthorize("hasAuthority('CREATE_TRAINEE')")
    @PostMapping
    public Submission create(@RequestBody SubmissionRequest submissionRequest) {
        return submissionService.create(submissionRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_TRAINEE')")
    @PutMapping("/{id}")
    public Submission update(@PathVariable Long id, @RequestBody SubmissionRequest submissionRequest) {
        return submissionService.update(id, submissionRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_TRAINEE')")
    @DeleteMapping("/{id}")
    public Submission delete(@PathVariable Long id) {
        return submissionService.delete(id);
    }
}
