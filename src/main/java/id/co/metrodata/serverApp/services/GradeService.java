package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Grade;
import id.co.metrodata.serverApp.models.Evaluation;
import id.co.metrodata.serverApp.models.Submission;
import id.co.metrodata.serverApp.models.dto.request.GradeRequest;
import id.co.metrodata.serverApp.repositories.GradeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private EmployeeService employeeService;
    private SubmissionService submissionService;

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not Found!"));
    }

    public Grade create(GradeRequest gradeRequest) {
        if (gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())) {
            for (Grade gradeCheck : gradeRepository.findAllBySegment_Id(gradeRequest.getSegmentId())) {
                if (Objects.equals(gradeCheck.getTrainee().getId(), gradeRequest.getTraineeId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "The grade in the segment already exists for that trainee!"
                    );
                }
            }
        }
        Grade grade = new Grade();
        grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));

//        set Average
        List<Submission> submissionsBySegment = new ArrayList<>();
        float result = 0f;
        for (Submission submission : submissionService.getByTraineeId(gradeRequest.getTraineeId())) {
            if (Objects.equals(submission.getTask().getSegment().getId(), gradeRequest.getSegmentId())) {
                submissionsBySegment.add(submission);
                result += submission.getNilai();
            }
        }
        float average = result/submissionsBySegment.size();
        grade.setAverage(average);

//        set Name and Status
        if (average >= 80) {
            grade.setName("A");
            grade.setStatus("Lulus");
        }
        else if (average >= 60){
            grade.setName("B");
            grade.setStatus("Lulus");
        }
        else if (average >= 40){
            grade.setName("C");
            grade.setStatus("Tidak Lulus");
        }
        else{
            grade.setName("D");
            grade.setStatus("Tidak Lulus");
        }
        return gradeRepository.save(grade);
    }

    public Grade update(Long id, GradeRequest gradeRequest) {
        Grade gradeOld = getById(id);
        if (!Objects.equals(gradeOld.getSegment().getId(), gradeRequest.getSegmentId()) && !Objects.equals(gradeOld.getTrainee().getId(), gradeRequest.getTraineeId())) {
            if (gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())) {
                for (Grade gradeCheck : gradeRepository.findAllBySegment_Id(gradeRequest.getSegmentId())) {
                    if (Objects.equals(gradeCheck.getTrainee().getId(), gradeRequest.getTraineeId())) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "The grade in the segment already exists for that trainee!"
                        );
                    }
                }
            }
        }
        Grade grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));

//        set Average
        List<Submission> submissionsBySegment = new ArrayList<>();
        float result = 0f;
        for (Submission submission : submissionService.getByTraineeId(gradeRequest.getTraineeId())) {
            if (Objects.equals(submission.getTask().getSegment().getId(), gradeRequest.getSegmentId())) {
                submissionsBySegment.add(submission);
                result += submission.getNilai();
            }
        }
        float average = result/submissionsBySegment.size();
        grade.setAverage(average);

//        set Name and Status
        if (average >= 80) {
            grade.setName("A");
            grade.setStatus("Lulus");
        }
        else if (average >= 60){
            grade.setName("B");
            grade.setStatus("Lulus");
        }
        else if (average >= 40){
            grade.setName("C");
            grade.setStatus("Tidak Lulus");
        }
        else{
            grade.setName("D");
            grade.setStatus("Tidak Lulus");
        }
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public Grade delete(Long id) {
        Grade grade = getById(id);
        gradeRepository.delete(grade);
        return grade;
    }
}
