package id.co.metrodata.serverApp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Submission;
import id.co.metrodata.serverApp.models.dto.request.SubmissionRequest;
import id.co.metrodata.serverApp.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private TaskService taskService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public List<Submission> getAll() {
        return submissionRepository.findAll();
    }

    public List<Submission> getByTask(Long id) {
        return submissionRepository.findByTask_Id(id);
    }

    public Submission getById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submission id not found"));
    }

    public Submission create(SubmissionRequest submissionRequest) {
        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        submission.setTask_id(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employeeService.getById(submissionRequest.getEmployeeId()));
        return submissionRepository.save(submission);
    }

    public Submission update(Long id, SubmissionRequest submissionRequest) {
        getById(id);
        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        submission.setId(id);
        submission.setTask_id(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employeeService.getById(submissionRequest.getEmployeeId()));
        return submissionRepository.save(submission);
    }

    public Submission delete(Long id) {
        Submission submission = getById(id);
        submissionRepository.delete(submission);
        return submission;
    }
}
