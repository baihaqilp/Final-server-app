package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Evaluation;
import id.co.metrodata.serverApp.models.dto.request.EvaluationRequest;
import id.co.metrodata.serverApp.repositories.EvaluationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationService {
    private EvaluationRepository evaluationRepository;
    private ModelMapper modelMapper;
    private GradeService gradeService;
    private SubmissionService submissionService;

    public List<Evaluation> getAll() {
        return evaluationRepository.findAll();
    }

    public Evaluation getById(Long id) {
        return evaluationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluation not Found!"));
    }

    public Evaluation create(EvaluationRequest evaluationRequest) {
        Evaluation evaluation = modelMapper.map(evaluationRequest, Evaluation.class);
        evaluation.setGrade(gradeService.getById(evaluationRequest.getGrade_id()));
        evaluation.setSubmission(submissionService.getById(evaluationRequest.getSubmission_id()));
        return evaluationRepository.save(evaluation);
    }

    public Evaluation update(Long id, EvaluationRequest evaluationRequest) {
        getById(id);
        Evaluation evaluation = modelMapper.map(evaluationRequest, Evaluation.class);
        evaluation.setGrade(gradeService.getById(evaluationRequest.getGrade_id()));
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
