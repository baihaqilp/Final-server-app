package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;
    private SegmentService segmentService;

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom id not found"));
    }

    public Classroom create(Classroom classroom) {
        // Segment segments = modelMapper.map(classRequest, Segment.class);
        // segments.setClassroom(getById(classRequest.getClass_id()));
        // segments.setEmployee(employeeService.getById(classRequest.getTrainer_id()));
        // createSegment(segments);
        // Classroom classroom = modelMapper.map(classRequest, Classroom.class);
        // classroom.setName(classroom.getName());
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
