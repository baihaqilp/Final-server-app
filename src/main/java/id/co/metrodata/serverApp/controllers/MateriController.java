package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Materi;
import id.co.metrodata.serverApp.models.dto.request.MateriRequest;
import id.co.metrodata.serverApp.services.MateriService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/materi")
public class MateriController {
    private MateriService materiService;

    @GetMapping
    public List<Materi> getAll() {
        return materiService.getAll();
    }

    @GetMapping("/{id}")
    public Materi getById(@PathVariable Long id) {
        return materiService.getById(id);
    }

    @GetMapping("/trainer")
    public List<Materi> getByTrainer() {
        return materiService.getByTrainer();
    }

    @GetMapping("/topic/{id}")
    public List<Materi> getByTopicId(@PathVariable Long id) {
        return materiService.getByTopicId(id);
    }

    @PostMapping
    public Materi create(@RequestBody MateriRequest materiRequest) {
        return materiService.create(materiRequest);
    }

    @PutMapping("/{id}")
    public Materi update(@PathVariable Long id, @RequestBody MateriRequest materiRequest) {
        return materiService.update(id, materiRequest);
    }

    @DeleteMapping("/{id}")
    public Materi delete(@PathVariable Long id) {
        return materiService.delete(id);
    }
}
