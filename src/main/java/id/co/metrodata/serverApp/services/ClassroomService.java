package id.co.metrodata.serverApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.repositories.ClassroomRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;
    private ProgramService programService;
    private ModelMapper modelMapper;
    private UserService userService;

    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    public Classroom getByTraine() {
        User user = userService.getByUsername();
        return classroomRepository.findByTraineeClass(user.getUsername());
    }

    public List<Classroom> getByProgram(Long id) {
        return classroomRepository.findAllByProgram_Id(id);
    }

    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Classroom id not found"));
    }

    public Classroom create(ClassroomRequest classroomRequest) {
        if (classroomRepository.existsByProgram_Id(classroomRequest.getProgramId())) {
            for (Classroom classroomCheck : classroomRepository.findAllByProgram_Id(classroomRequest.getProgramId())) {
                if (Objects.equals(classroomCheck.getName(), classroomRequest.getName())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Classroom name in the same program is already exists!");
                }
            }
        }
        Classroom classroom = modelMapper.map(classroomRequest, Classroom.class);
        classroom.setProgram(programService.getById(classroomRequest.getProgramId()));
        return classroomRepository.save(classroom);
    }

    public Classroom update(Long id, ClassroomRequest classroomRequest) {
        Classroom classroomOld = getById(id);
        if (classroomRepository.existsByProgram_Id(classroomRequest.getProgramId())) {
            for (Classroom classroomCheck : classroomRepository.findAllByProgram_Id(classroomRequest.getProgramId())) {
                if (!Objects.equals(classroomOld.getName(), classroomCheck.getName())) {
                    if (Objects.equals(classroomCheck.getName(), classroomRequest.getName())) {
                        throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Classroom name in the same program is already exists!");
                    }
                }
            }
        }
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
