package id.co.metrodata.serverApp.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import id.co.metrodata.serverApp.models.Task;
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
    public List<Submission> getByTraineeId(Long id) {
        return submissionRepository.findAllByEmployee_Id(id);
    }

    public Submission getById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submission id not found"));
    }

    public Submission create(SubmissionRequest submissionRequest) {
        Task findTask = taskService.getById(submissionRequest.getTaskId());
        if (findTask.getDeadline().isBefore(submissionRequest.getSubmission_date())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE,
                "Submission has passed the specified time limit!"
            );
        }
        if (!submissionRequest.getSubmission_file().endsWith(".pdf")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Make sure the submission file is a PDF!"
            );
        }
        if (submissionRepository.existsByTask_Id(submissionRequest.getTaskId())) {
            for (Submission submissionCheck : submissionRepository.findAllByTask_Id(submissionRequest.getTaskId())) {
                if (Objects.equals(submissionCheck.getEmployee().getId(), submissionRequest.getEmployeeId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Trainee have submitted submissions on the task!"
                    );
                }
            }
        }
        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        submission.setTask(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employeeService.getById(submissionRequest.getEmployeeId()));
        return submissionRepository.save(submission);
    }

    public Submission update(Long id, SubmissionRequest submissionRequest) {
        if (!submissionRequest.getSubmission_file().endsWith(".pdf")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Make sure the submission file is a PDF!"
            );
        }
        Submission submissionOld = getById(id);
        if (!Objects.equals(submissionOld.getTask().getId(), submissionRequest.getTaskId()) && !Objects.equals(submissionOld.getEmployee().getId(), submissionRequest.getEmployeeId())) {
            if (submissionRepository.existsByTask_Id(submissionRequest.getTaskId())) {
                for (Submission submissionCheck : submissionRepository.findAllByTask_Id(submissionRequest.getTaskId())) {
                    if (Objects.equals(submissionCheck.getEmployee().getId(), submissionRequest.getEmployeeId())) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Trainee have submitted submissions on the task!"
                        );
                    }
                }
            }
        }
        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        submission.setId(id);
        submission.setTask(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employeeService.getById(submissionRequest.getEmployeeId()));
        return submissionRepository.save(submission);
    }

    public Submission delete(Long id) {
        Submission submission = getById(id);
        submissionRepository.delete(submission);
        return submission;
    }
}
