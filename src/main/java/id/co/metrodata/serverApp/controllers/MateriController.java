package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.Materi;
import id.co.metrodata.serverApp.models.dto.MateriRequest;
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
    @PostMapping
    public Materi create(@RequestBody MateriRequest materiRequest) {
        return materiService.create(materiRequest);
    }
    @PutMapping("/{id}")
    public Materi update(@PathVariable Long id, @RequestBody Materi materi) {
        return materiService.update(id, materi);
    }
    @DeleteMapping("/{id}")
    public Materi delete(@PathVariable Long id) {
        return materiService.delete(id);
    }
}
