package id.co.metrodata.serverApp.services;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.co.metrodata.serverApp.models.Segment;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.SegmentRequest;
import id.co.metrodata.serverApp.repositories.SegmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SegmentService {
    private SegmentRepository segmentRepository;
    private ClassroomService classroomService;
    private EmployeeService employeeService;
    private UserService userService;
    private ModelMapper modelMapper;

    public List<Segment> getAll() {
        return segmentRepository.findAll();
    }

    public List<Segment> getSegmentClass(Long id) {
        return segmentRepository.findAllByClassroom_Id(id);
    }

    public List<Segment> getSegmentByEnddate(Date date) {
        return segmentRepository.findByEnddate(date);
    }

    public List<Segment> getSegmentTrainer() {
        User user = userService.getByUsername();
        return segmentRepository.findAllBySegmentTrainerGroup(user.getId());
    }

    public List<Segment> getSegmentClassTrainer(Long classroom_id) {
        User user = userService.getByUsername();
        return segmentRepository.findAllBySegmentClassTrainer(classroom_id, user.getId());
    }

    public Segment getById(Long id) {
        return segmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    public Segment create(SegmentRequest segmentRequest) {
        Segment segment = modelMapper.map(segmentRequest, Segment.class);

        segment.setClassroom(classroomService.getById(segmentRequest.getClassroomId()));
        segment.setTrainer(employeeService.getById(segmentRequest.getTrainerId()));
        return segmentRepository.save(segment);
    }

    public Segment update(Long id, SegmentRequest segmentRequest) {
        getById(id);
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

}
