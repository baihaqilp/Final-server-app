package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;
    private ProgramService programService;
    private ModelMapper modelMapper;

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public List<Classroom> getByProgram(Long id) {
        return classroomRepository.findAllByProgram_Id(id);
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom id not found"));
    }

    public Classroom create(ClassroomRequest classroomRequest) {
        Classroom classroom = modelMapper.map(classroomRequest, Classroom.class);
        classroom.setProgram(programService.getById(classroomRequest.getProgramId()));
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, ClassroomRequest classroomRequest) {
        getById(id);
        Classroom classroom = modelMapper.map(classroomRequest, Classroom.class);
        classroom.setId(id);
        classroom.setProgram(programService.getById(classroomRequest.getProgramId()));
        return classroomRepository.save(classroom);
    }

    public Classroom delete(Long id) {
        Classroom classroom = getById(id);
        classroomRepository.delete(classroom);
        return classroom;
    }

}
