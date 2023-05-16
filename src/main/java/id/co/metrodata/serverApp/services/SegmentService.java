package id.co.metrodata.serverApp.services;

import java.util.List;

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

    public List<Segment> getAll() {
        return segmentRepository.findAll();
    }

    public List<Segment> getSegmentClass(Long id) {
        // List<Employee> employees = segmentRepository.findAllByClassroom_Id(id);
        return segmentRepository.findAllByClassroom_Id(id);
    }

    public Segment getById(Long id) {
        return segmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Not Found"));
    }

    public Segment create(SegmentRequest segmentRequest) {
        Segment segment = new Segment();
        segment.setClassroom(classroomService.getById(segmentRequest.getClassroomId()));
        segment.setEmployee(employeeService.getById(segmentRequest.getTrainerId()));

        return segmentRepository.save(segment);
    }

    public Segment update(Long id, SegmentRequest segmentRequest) {
        getById(id);
        Segment segment = new Segment();
        segment.setId(id);
        segment.setClassroom(classroomService.getById(segmentRequest.getClassroomId()));
        segment.setEmployee(employeeService.getById(segmentRequest.getTrainerId()));
        return segmentRepository.save(segment);
    }

    public Segment delete(Long id) {
        Segment segment = getById(id);
        segmentRepository.delete(segment);
        return segment;
    }

}
