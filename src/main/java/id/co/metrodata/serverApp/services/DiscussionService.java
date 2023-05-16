package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Discussion;
import id.co.metrodata.serverApp.models.dto.request.DiscussionRequest;
import id.co.metrodata.serverApp.repositories.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscussionService {
    private DiscussionRepository discussionRepository;
    private ModelMapper modelMapper;
    private MateriService materiService;
    private EmployeeService employeeService;
    public List<Discussion> getAll() {
        return discussionRepository.findAll();
    }
    public Discussion getById(Long id) {
        return discussionRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Discussion not Found!")
                );
    }
    public Discussion create(DiscussionRequest discussionRequest) {
        Discussion discussion = modelMapper.map(discussionRequest, Discussion.class);
        discussion.setMateri(materiService.getById(discussionRequest.getMateriId()));
        discussion.setEmployee(employeeService.getById(discussionRequest.getEmployeeId()));
        return discussionRepository.save(discussion);
    }
    public Discussion update(DiscussionRequest discussionRequest, Long id) {
        getById(id);
        Discussion discussion = modelMapper.map(discussionRequest, Discussion.class);
        discussion.setMateri(materiService.getById(discussionRequest.getMateriId()));
        discussion.setEmployee(employeeService.getById(discussionRequest.getEmployeeId()));
        discussion.setId(id);
        return discussionRepository.save(discussion);
    }
    public Discussion delete(Long id) {
        Discussion discussion = getById(id);
        discussionRepository.delete(discussion);
        return discussion;
    }
}
