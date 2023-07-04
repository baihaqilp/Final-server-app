package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Evaluation;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.EvaluationRequest;
import id.co.metrodata.serverApp.repositories.EvaluationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EvaluationService {
    private EvaluationRepository evaluationRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private SubmissionService submissionService;
    private UserService userService;

    public List<Evaluation> getAll() {
        return evaluationRepository.findAll();
    }

    public List<Evaluation> getBySubmission(Long id) {
        return evaluationRepository.findAllBySubmission_Id(id);
    }

    public List<Evaluation> getByTask(Long id) {
        return evaluationRepository.findAllBySubmission_Task_Id(id);
    }

    public Evaluation getById(Long id) {
        return evaluationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation not Found!"));
    }

    public Evaluation create(EvaluationRequest evaluationRequest) {
        User trainer = userService.getByUsername();
        if (evaluationRepository.existsByTrainer_Id(trainer.getId())) {
            for (Evaluation evaluationCheck : evaluationRepository
                    .findAllByTrainer_Id(trainer.getId())) {
                if (Objects.equals(evaluationCheck.getSubmission().getId(), evaluationRequest.getSubmission_id())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "The trainer has evaluated the submission!");
                }
            }
        }
        Evaluation evaluation = modelMapper.map(evaluationRequest, Evaluation.class);
        evaluation.setTrainer(employeeService.getById(trainer.getId()));
        evaluation.setSubmission(submissionService.getById(evaluationRequest.getSubmission_id()));
        return evaluationRepository.save(evaluation);
    }

    public Evaluation update(Long id, EvaluationRequest evaluationRequest) {
        getById(id);
        User trainer = userService.getByUsername();
        Evaluation evaluation = modelMapper.map(evaluationRequest, Evaluation.class);
        evaluation.setTrainer(employeeService.getById(trainer.getId()));
        evaluation.setSubmission(submissionService.getById(evaluationRequest.getSubmission_id()));
        evaluation.setId(id);
        return evaluationRepository.save(evaluation);
    }

    public Evaluation delete(Long id) {
        Evaluation evaluation = getById(id);
        evaluationRepository.delete(evaluation);
        return evaluation;
    }
}
