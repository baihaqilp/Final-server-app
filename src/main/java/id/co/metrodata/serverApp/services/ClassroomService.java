package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;
    private ProgramService programService;

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom id not found"));
    }

    public Classroom create(ClassroomRequest classroomRequest) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomRequest.getName());
        classroom.setProgram(programService.getById(classroomRequest.getProgramId()));
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, ClassroomRequest classroomRequest) {
        getById(id);
        Classroom classroom = new Classroom();
        classroom.setId(id);
        classroom.setName(classroomRequest.getName());
        classroom.setProgram(programService.getById(classroomRequest.getProgramId()));
        return classroomRepository.save(classroom);
    }

    public Classroom delete(Long id) {
        Classroom classroom = getById(id);
        classroomRepository.delete(classroom);
        return classroom;
    }

}
