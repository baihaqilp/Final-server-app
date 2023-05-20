package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Grade;
import id.co.metrodata.serverApp.models.dto.request.GradeRequest;
import id.co.metrodata.serverApp.repositories.GradeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private EmployeeService employeeService;

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not Found!"));
    }

    public Grade create(GradeRequest gradeRequest) {
        if (
            gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())
            &&
            gradeRepository.existsByTrainee_Id(gradeRequest.getTraineeId())
        ) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Grade sudah ada untuk Trainee tersebut!"
            );
        }
        Grade grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));
        return gradeRepository.save(grade);
    }

    public Grade update(Long id, GradeRequest gradeRequest) {
        if (
                gradeRepository.existsBySegment_Id(gradeRequest.getSegmentId())
                        &&
                        gradeRepository.existsByTrainee_Id(gradeRequest.getTraineeId())
        ) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Grade sudah ada untuk Trainee tersebut!"
            );
        }
        getById(id);
        Grade grade = modelMapper.map(gradeRequest, Grade.class);
        grade.setSegment(segmentService.getById(gradeRequest.getSegmentId()));
        grade.setTrainee(employeeService.getById(gradeRequest.getTraineeId()));
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public Grade delete(Long id) {
        Grade grade = getById(id);
        gradeRepository.delete(grade);
        return grade;
    }
}
