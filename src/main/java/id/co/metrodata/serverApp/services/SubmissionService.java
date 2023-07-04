package id.co.metrodata.serverApp.services;

import java.util.List;
import java.util.Objects;

import id.co.metrodata.serverApp.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.dto.request.SubmissionRequest;
import id.co.metrodata.serverApp.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private TaskService taskService;
    private EmployeeService employeeService;
    private UserService userService;
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

    public Submission getByTaskTrainee(Long id) {
        User user = userService.getByUsername();
        return submissionRepository.findAllByTaskTrainee(id, user.getId());
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
                    "Submission has passed the specified time limit!");
        }
        if (!submissionRequest.getSubmission_file().endsWith(".pdf")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Make sure the submission file is a PDF!");
        }
        User user = userService.getByUsername();
        Employee employee = employeeService.getById(user.getId());
        if (submissionRepository.existsByTask_Id(submissionRequest.getTaskId())) {
            for (Submission submissionCheck : submissionRepository.findAllByTask_Id(submissionRequest.getTaskId())) {
                if (Objects.equals(submissionCheck.getEmployee().getId(), employee.getId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Trainee have submitted submissions on the task!");
                }
            }
        }
        // find trainer
        Task findTrainer = taskService.getById(submissionRequest.getTaskId());

        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        Evaluation evaluation = modelMapper.map(submissionRequest, Evaluation.class);

        // set evaluation
        evaluation.setNilai(0);
        evaluation.setTrainer(findTrainer.getSegment().getTrainer());
        evaluation.setSubmission(submission);

        // set submission
        submission.setTask(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employee);
        submission.setEvaluation(evaluation);
        return submissionRepository.save(submission);
    }

    public Submission update(Long id, SubmissionRequest submissionRequest) {
        if (!submissionRequest.getSubmission_file().endsWith(".pdf")) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Make sure the submission file is a PDF!");
        }
        User user = userService.getByUsername();
        Employee employee = employeeService.getById(user.getId());
        Submission submissionOld = getById(id);
        if (!Objects.equals(submissionOld.getTask().getId(), submissionRequest.getTaskId())
                && !Objects.equals(submissionOld.getEmployee().getId(), employee.getId())) {
            if (submissionRepository.existsByTask_Id(submissionRequest.getTaskId())) {
                for (Submission submissionCheck : submissionRepository
                        .findAllByTask_Id(submissionRequest.getTaskId())) {
                    if (Objects.equals(submissionCheck.getEmployee().getId(), employee.getId())) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Trainee have submitted submissions on the task!");
                    }
                }
            }
        }
        // Submission submission = modelMapper.map(submissionRequest, Submission.class);
        // submission.setTask(taskService.getById(submissionRequest.getTaskId()));
        // submission.setEmployee(employee);

        // find trainer
        Task findTrainer = taskService.getById(submissionRequest.getTaskId());

        Submission submission = modelMapper.map(submissionRequest, Submission.class);
        submission.setId(id);
        // Evaluation evaluation = new Evaluation();

        // set evaluation
        // evaluation.setNilai(submissionOld.getEvaluation().getNilai());
        // evaluation.setTrainer(findTrainer.getSegment().getTrainer());
        // evaluation.setSubmission(submission);

        // set submission
        submission.setTask(taskService.getById(submissionRequest.getTaskId()));
        submission.setEmployee(employee);
        submission.setEvaluation(submissionOld.getEvaluation());
        return submissionRepository.save(submission);
    }

    public Submission delete(Long id) {
        Submission submission = getById(id);
        submissionRepository.delete(submission);
        return submission;
    }
}
