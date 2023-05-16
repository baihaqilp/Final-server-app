package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Grade;
import id.co.metrodata.serverApp.repositories.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private GradeRepository gradeRepository;

    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    public Grade getById(Long id) {
        return gradeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grade not Found!"));
    }

    public Grade create(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade update(Long id, Grade grade) {
        getById(id);
        grade.setId(id);
        return gradeRepository.save(grade);
    }

    public Grade delete(Long id) {
        Grade grade = getById(id);
        gradeRepository.delete(grade);
        return grade;
    }
}
