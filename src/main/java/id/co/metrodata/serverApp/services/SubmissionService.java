package id.co.metrodata.serverApp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Submission;
import id.co.metrodata.serverApp.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService {
    private SubmissionRepository submissionRepository;

    public List<Submission> getAll() {
        return submissionRepository.findAll();
    }

    public Submission getById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submission id not found"));
    }

    public Submission create(Submission submission) {
        return submissionRepository.save(submission);
    }

    public Submission update(Long id, Submission submission) {
        getById(id);
        submission.setId(id);
        return submissionRepository.save(submission);
    }

    public Submission delete(Long id) {
        Submission submission = getById(id);
        submissionRepository.delete(submission);
        return submission;
    }
}
