package id.co.metrodata.serverApp.services;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.repositories.SegmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SegmentService {
    private SegmentRepository segmentRepository;
    private ClassroomService classroomService;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public List<Segment> getAll() {
        return segmentRepository.findAll();
    }

    public List<Segment> getSegmentClass(Long id) {
        // List<Employee> employees = segmentRepository.findAllByClassroom_Id(id);
        return segmentRepository.findAllByClassroom_Id(id);
    }

    public List<Segment> getSegmentTrainer(Long id) {
        return segmentRepository.findAllBySegmentTrainerGroup(id);
    }

    public List<Segment> getSegmentClassTrainer(Long classroom_id, Long trainer_id) {
        return segmentRepository.findAllBySegmentClassTrainer(classroom_id, trainer_id);
    }

    public Segment getById(Long id) {
        return segmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    public Segment create(SegmentRequest segmentRequest) {
        // if (segmentRepository.existsByClassroom_Id(segmentRequest.getClassroomId()))
        // {
        // for (Segment segmentCheck :
        // segmentRepository.findAllByClassroom_Id(segmentRequest.getClassroomId())) {
        // if (Objects.equals(segmentCheck.getTrainer().getId(),
        // segmentRequest.getTrainerId())) {
        // throw new ResponseStatusException(
        // HttpStatus.CONFLICT,
        // "The trainer has filled out the segment in another class!");
        // }
        // }
        // }
        Segment segment = modelMapper.map(segmentRequest, Segment.class);

        segment.setClassroom(classroomService.getById(segmentRequest.getClassroomId()));
        segment.setTrainer(employeeService.getById(segmentRequest.getTrainerId()));
        return segmentRepository.save(segment);
    }

    public Segment update(Long id, SegmentRequest segmentRequest) {
        getById(id);
        // if (!Objects.equals(segmentOld.getClassroom().getId(),
        // segmentRequest.getClassroomId())
        // && !Objects.equals(segmentOld.getTrainer().getId(),
        // segmentRequest.getTrainerId())) {
        // if (segmentRepository.existsByClassroom_Id(segmentRequest.getClassroomId()))
        // {
        // for (Segment segmentCheck :
        // segmentRepository.findAllByClassroom_Id(segmentRequest.getClassroomId())) {
        // if (Objects.equals(segmentCheck.getTrainer().getId(),
        // segmentRequest.getTrainerId())) {
        // throw new ResponseStatusException(
        // HttpStatus.CONFLICT,
        // "The trainer has filled out the segment in another class!");
        // }
        // }
        // }
        // }
        Segment segment = modelMapper.map(segmentRequest, Segment.class);
        segment.setId(id);
        segment.setClassroom(classroomService.getById(segmentRequest.getClassroomId()));
        segment.setTrainer(employeeService.getById(segmentRequest.getTrainerId()));
        return segmentRepository.save(segment);
    }

    public Segment delete(Long id) {
        Segment segment = getById(id);
        segmentRepository.delete(segment);
        return segment;
    }

    // public Segment changeMateri(Long id, ChangeMateriRequest changeMateriRequest)
    // {
    // Segment segment = getById(id);
    // List<Topic> topics = segment.get();
    // if (changeMateriRequest.getTopics().length != 0) {
    // for (Long topicId : changeMateriRequest.getTopics()) {
    // topics.add(topicService.getById(topicId));
    // }
    // }
    // segment.setTopics(topics);
    // return segmentRepository.save(segment);
    // }
    // public Segment editMateri(Long id, Long[] materiList) {
    // Segment segment = getById(id);
    // List<Materi> materis = segment.getMateris();
    // for (Long materi : materiList) {
    // materis.add(materiService.getById(materi));
    // }
    // segment.setMateris(materis);
    // return segmentRepository.save(segment);
    // }
    // public Segment deleteMateri(Long id, Long[] materiList) {
    // Segment segment = getById(id);
    // List<Materi> materis = segment.getMateris();
    // for (Long materi : materiList) {
    // materis.add(materiService.getById(materi));
    // }
    // segment.setMateris(materis);
    // return segmentRepository.save(segment);
    // }
}
