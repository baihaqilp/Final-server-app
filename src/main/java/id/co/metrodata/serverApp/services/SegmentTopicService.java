package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.SegmentTopic;
import id.co.metrodata.serverApp.models.dto.request.SegmentTopicRequest;
import id.co.metrodata.serverApp.repositories.SegmentTopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SegmentTopicService {
    private SegmentTopicRepository segmentTopicRepository;
    private ModelMapper modelMapper;
    private SegmentService segmentService;
    private TopicService topicService;

    public List<SegmentTopic> getAll() {
        return segmentTopicRepository.findAll();
    }

    public SegmentTopic getById(Long id) {
        return segmentTopicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SegmentMateri not Found!"));
    }

    public SegmentTopic create(SegmentTopicRequest segmentTopicRequest) {
        if (segmentTopicRepository.existsBySegment_Id(segmentTopicRequest.getSegmentId())) {
            for (SegmentTopic segmentTopicCheck : segmentTopicRepository
                    .findAllBySegment_Id(segmentTopicRequest.getSegmentId())) {
                if (Objects.equals(segmentTopicCheck.getTopic().getMateris().get(0).getId(),
                        segmentTopicRequest.getTopicId())) {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "The materi has been added to the segment!");
                }
            }
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
