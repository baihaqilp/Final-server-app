package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Materi;
import id.co.metrodata.serverApp.models.User;
import id.co.metrodata.serverApp.models.dto.request.MateriRequest;
import id.co.metrodata.serverApp.repositories.MateriRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class MateriService {
    private MateriRepository materiRepository;
    private ModelMapper modelMapper;
    private TopicService topicService;
    private EmployeeService employeeService;
    private UserService userService;

    public List<Materi> getAll() {
        return materiRepository.findAllByOrder();
    }

    public List<Materi> getByTopicId(Long id) {
        return materiRepository.findAllByTopic_Id(id);
    }

    public List<Materi> getByTrainer() {
        User user = userService.getByUsername();
        return materiRepository.findAllByEmployee_Id(user.getId());
    }

    public Materi getById(Long id) {
        return materiRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materi not Found!"));
    }

    public Materi create(MateriRequest materiRequest) {
        User user = userService.getByUsername();
        Materi materi = modelMapper.map(materiRequest, Materi.class);
        materi.setTopic(topicService.getById(materiRequest.getTopicId()));
        materi.setEmployee(employeeService.getById(user.getId()));
        return materiRepository.save(materi);
    }

    public Materi update(Long id, MateriRequest materiRequest) {
        User user = userService.getByUsername();
        getById(id);
        Materi materi = modelMapper.map(materiRequest, Materi.class);
        materi.setId(id);
        materi.setTopic(topicService.getById(materiRequest.getTopicId()));
        materi.setEmployee(employeeService.getById(user.getId()));
        return materiRepository.save(materi);
    }

    public Materi delete(Long id) {
        Materi materi = getById(id);
        materiRepository.delete(materi);
        return materi;
    }
}
