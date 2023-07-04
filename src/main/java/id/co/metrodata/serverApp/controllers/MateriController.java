package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Materi;
import id.co.metrodata.serverApp.models.dto.request.MateriRequest;
import id.co.metrodata.serverApp.services.MateriService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/materi")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class MateriController {
    private MateriService materiService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Materi> getAll() {
        return materiService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Materi getById(@PathVariable Long id) {
        return materiService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/trainer")
    public List<Materi> getByTrainer() {
        return materiService.getByTrainer();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/topic/{id}")
    public List<Materi> getByTopicId(@PathVariable Long id) {
        return materiService.getByTopicId(id);
    }

    @PreAuthorize("hasAuthority('CREATE_TRAINER')")
    @PostMapping
    public Materi create(@RequestBody MateriRequest materiRequest) {
        return materiService.create(materiRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_TRAINER')")
    @PutMapping("/{id}")
    public Materi update(@PathVariable Long id, @RequestBody MateriRequest materiRequest) {
        return materiService.update(id, materiRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_TRAINER')")
    @DeleteMapping("/{id}")
    public Materi delete(@PathVariable Long id) {
        return materiService.delete(id);
    }
}
