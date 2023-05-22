package id.co.metrodata.serverApp.controllers;

import id.co.metrodata.serverApp.models.SegmentTopic;
import id.co.metrodata.serverApp.models.dto.request.SegmentTopicRequest;
import id.co.metrodata.serverApp.services.SegmentTopicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/segmenttopic")
public class SegmentTopicController {
    private SegmentTopicService segmentTopicService;

    @GetMapping
    public List<SegmentTopic> getAll() {
        return segmentTopicService.getAll();
    }

    @GetMapping("/{id}")
    public SegmentTopic getById(@PathVariable Long id) {
        return segmentTopicService.getById(id);
    }

    @PostMapping
    public SegmentTopic create(@RequestBody SegmentTopicRequest segmentTopicRequest) {
        return segmentTopicService.create(segmentTopicRequest);
    }

    @PutMapping("/{id}")
    public SegmentTopic update(@PathVariable Long id, @RequestBody SegmentTopicRequest segmentTopicRequest) {
        return segmentTopicService.update(id, segmentTopicRequest);
    }

    @DeleteMapping("/{id}")
    public SegmentTopic delete(@PathVariable Long id) {
        return segmentTopicService.delete(id);
    }
}
