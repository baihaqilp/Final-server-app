package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.SegmentTopic;
import id.co.metrodata.serverApp.models.dto.request.SegmentTopicRequest;
import id.co.metrodata.serverApp.services.SegmentTopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/segmenttopic")
@PreAuthorize("hasAnyRole('ADMIN', 'TRAINER', 'TRAINEE')")
public class SegmentTopicController {
    private SegmentTopicService segmentTopicService;

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER')")
    @GetMapping
    public List<SegmentTopic> getAll() {
        return segmentTopicService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_TRAINEE')")
    @GetMapping("/bysegment/{id}")
    public List<SegmentTopic> getBySegment(@PathVariable Long id) {
        return segmentTopicService.getBySegment(id);
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_TRAINER','READ_TRAINEE')")
    @GetMapping("/{id}")
    public SegmentTopic getById(@PathVariable Long id) {
        return segmentTopicService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('CREATE_ADMIN', 'CREATE_TRAINER')")
    @PostMapping
    public SegmentTopic create(@RequestBody SegmentTopicRequest segmentTopicRequest) {
        return segmentTopicService.create(segmentTopicRequest);
    }

    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_TRAINER')")
    @PutMapping("/{id}")
    public SegmentTopic update(@PathVariable Long id, @RequestBody SegmentTopicRequest segmentTopicRequest) {
        return segmentTopicService.update(id, segmentTopicRequest);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ADMIN', 'DELETE_TRAINER')")
    @DeleteMapping("/{id}")
    public SegmentTopic delete(@PathVariable Long id) {
        return segmentTopicService.delete(id);
    }
}
