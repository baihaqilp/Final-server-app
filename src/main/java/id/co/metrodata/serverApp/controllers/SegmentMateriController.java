package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.SegmentMateri;
import id.co.metrodata.serverApp.models.dto.request.SegmentMateriRequest;
import id.co.metrodata.serverApp.services.SegmentMateriService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/segmentmateri")
public class SegmentMateriController {
    private SegmentMateriService segmentMateriService;
    @GetMapping
    public List<SegmentMateri> getAll() {
        return segmentMateriService.getAll();
    }

    @GetMapping("/{id}")
    public SegmentMateri getById(@PathVariable Long id) {
        return segmentMateriService.getById(id);
    }

    @PostMapping
    public SegmentMateri create(@RequestBody SegmentMateriRequest segmentMateriRequest) {
        return segmentMateriService.create(segmentMateriRequest);
    }

    @PutMapping("/{id}")
    public SegmentMateri update(@PathVariable Long id, @RequestBody SegmentMateriRequest segmentMateriRequest) {
        return segmentMateriService.update(id, segmentMateriRequest);
    }

    @DeleteMapping("/{id}")
    public SegmentMateri delete(@PathVariable Long id) {
        return segmentMateriService.delete(id);
    }
}
