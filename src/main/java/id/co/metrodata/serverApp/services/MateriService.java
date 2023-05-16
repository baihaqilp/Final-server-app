package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Materi;
import id.co.metrodata.serverApp.models.dto.MateriRequest;
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

    public List<Materi> getAll() {
        return materiRepository.findAll();
    }

    public Materi getById(Long id) {
        return materiRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materi not Found!"));
    }

    public Materi create(MateriRequest materiRequest) {
        Materi materi = modelMapper.map(materiRequest, Materi.class);
        materi.setTopic(topicService.getById(materiRequest.getTopicId()));
        return materiRepository.save(materi);
    }

    public Materi update(Long id, MateriRequest materiRequest) {
        getById(id);
        Materi materi = modelMapper.map(materiRequest, Materi.class);
        materi.setTopic(topicService.getById(materiRequest.getTopicId()));
        materi.setId(id);
        return materiRepository.save(materi);
    }

    public Materi delete(Long id) {
        Materi materi = getById(id);
        materiRepository.delete(materi);
        return materi;
    }
}
