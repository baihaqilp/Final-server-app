package id.co.metrodata.serverApp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom id not found"));
    }

    public Classroom create(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, Classroom classroom) {
        getById(id);
        classroom.setId(id);
        return classroomRepository.save(classroom);
    }

    public Classroom delete(Long id) {
        Classroom classroom = getById(id);
        classroomRepository.delete(classroom);
        return classroom;
    }
}
