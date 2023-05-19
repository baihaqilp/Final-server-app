package id.co.metrodata.serverApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.services.SegmentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/segment")
@AllArgsConstructor
public class SegmentController {
    private SegmentService segmentService;

    @GetMapping
    public List<Segment> getAll() {
        return segmentService.getAll();
    }

    @GetMapping("/{id}")
    public Segment getById(@PathVariable Long id) {
        return segmentService.getById(id);
    }

    @GetMapping("/class/{id}")
    public List<Segment> getSegmentClass(@PathVariable Long id) {
        return segmentService.getSegmentClass(id);
    }

    @PutMapping("/{id}")
    public Segment update(@PathVariable Long id, @RequestBody SegmentRequest segmentRequest) {
        return segmentService.update(id, segmentRequest);
    }

    @PostMapping
    public Segment create(@RequestBody SegmentRequest segmentRequest) {
        return segmentService.create(segmentRequest);
    }

    @DeleteMapping("/{id}")
    public Segment delete(@PathVariable Long id) {
        return segmentService.delete(id);
    }

//    @PostMapping("/{id}")
//    public Segment changeTopic(@PathVariable Long id, @RequestBody ChangeMateriRequest changeMateriRequest) {
//        return segmentService.changeTopic(id, changeMateriRequest);
//    }
}
