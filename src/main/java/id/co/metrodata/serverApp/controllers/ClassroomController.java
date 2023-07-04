package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.Classroom;
import id.co.metrodata.serverApp.models.dto.request.ClassroomRequest;
import id.co.metrodata.serverApp.services.ClassroomService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/classroom")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class ClassroomController {
    private ClassroomService classroomService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping
    public List<Classroom> getActive() {
        return classroomService.getActive();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/noactive")
    public List<Classroom> getNonActive() {
        return classroomService.getNonACtive();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/program/{id}")
    public List<Classroom> getByProgram(@PathVariable Long id) {
        return classroomService.getByProgram(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/status/{status}")
    public List<Classroom> getByStatus(@PathVariable String status) {
        return classroomService.getByStatus(status);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Classroom getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/trainee")
    public Classroom getByTrainee() {
        return classroomService.getByTraine();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/trainer-active")
    public List<Classroom> getByTrainer() {
        return classroomService.getActiveByTrainer();
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Classroom create(@RequestBody ClassroomRequest classroomRequest) {
        return classroomService.create(classroomRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Classroom update(@PathVariable Long id, @RequestBody ClassroomRequest classroomRequest) {
        return classroomService.update(id, classroomRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Classroom delete(@PathVariable Long id) {
        return classroomService.delete(id);
    }
}
