package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.SegmentTopic;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.SegmentTopicRequest;
import id.co.metrodata.serverApp.repositories.SegmentTopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class SegmentTopicService {
    private SegmentTopicRepository segmentTopicRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private TopicService topicService;
    private UserService userService;

    public List<SegmentTopic> getAll() {
        return segmentTopicRepository.findAll();
    }

    public List<SegmentTopic> getBySegment(Long id) {
        User user = userService.getByUsername();
        // Segment segment =
        // segmentService.findByClass(user.getEmployee().getClassroom().getId());
        return segmentTopicRepository.findAllBySegmentOrderCategory(id, user.getEmployee().getClassroom().getId());
    }

    public SegmentTopic getById(Long id) {
        return segmentTopicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SegmentMateri not Found!"));
    }

    public SegmentTopic create(SegmentTopicRequest segmentTopicRequest) {
        if (segmentTopicRepository.existsByTopic_IdAndSegment_Id(segmentTopicRequest.getTopicId(),
                segmentTopicRequest.getSegmentId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Topic has been added to the segment!");
        }
        SegmentTopic segmentTopic = modelMapper.map(segmentTopicRequest, SegmentTopic.class);
        segmentTopic.setSegment(segmentService.getById(segmentTopicRequest.getSegmentId()));
        segmentTopic.setTopic(topicService.getById(segmentTopicRequest.getTopicId()));
        return segmentTopicRepository.save(segmentTopic);
    }

    public SegmentTopic update(Long id, SegmentTopicRequest segmentTopicRequest) {
        getById(id);
        SegmentTopic segmentTopic2 = modelMapper.map(segmentTopicRequest, SegmentTopic.class);
        segmentTopic2.setSegment(segmentService.getById(segmentTopicRequest.getSegmentId()));
        segmentTopic2.setTopic(topicService.getById(segmentTopicRequest.getTopicId()));
        segmentTopic2.setId(id);
        return segmentTopicRepository.save(segmentTopic2);
    }

    public SegmentTopic delete(Long id) {
        SegmentTopic segmentTopic = getById(id);
        segmentTopicRepository.delete(segmentTopic);
        return segmentTopic;
    }
}
