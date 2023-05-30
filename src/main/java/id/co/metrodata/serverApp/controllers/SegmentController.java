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

import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.services.SegmentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/segment")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class SegmentController {
    private SegmentService segmentService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping
    public List<Segment> getAll() {
        return segmentService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/{id}")
    public Segment getById(@PathVariable Long id) {
        return segmentService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER', 'READ_TRAINEE')")
    @GetMapping("/class/{id}")
    public List<Segment> getSegmentClass(@PathVariable Long id) {
        return segmentService.getSegmentClass(id);
    }

    @PreAuthorize("hasAuthority('READ_TRAINEE')")
    @GetMapping("/class/trainee")
    public List<Segment> getSegmentClassTrainee() {
        return segmentService.getSegmentClassTrainee();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/all")
    public List<Segment> getSegmentByTrainer() {
        return segmentService.getSegmentByTrainer();
    }

    // Group by
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/trainer")
    public List<Segment> getSegmentTrainer() {
        return segmentService.getSegmentTrainer();
    }

    // list segment
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping("/class/{classroom_id}/trainer")
    public List<Segment> getSegmentClassTrainer(@PathVariable Long classroom_id) {
        return segmentService.getSegmentClassTrainer(classroom_id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Segment update(@PathVariable Long id, @RequestBody SegmentRequest segmentRequest) {
        return segmentService.update(id, segmentRequest);
    }

    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Segment create(@RequestBody SegmentRequest segmentRequest) {
        return segmentService.create(segmentRequest);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Segment delete(@PathVariable Long id) {
        return segmentService.delete(id);
    }
}
