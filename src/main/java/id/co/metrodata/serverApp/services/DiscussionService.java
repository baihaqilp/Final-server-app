package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Discussion;
import id.co.metrodata.serverApp.repositories.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscussionService {
    private DiscussionRepository discussionRepository;
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
    public Discussion create(Discussion discussion) {
        return discussionRepository.save(discussion);
    }
    public Discussion update(Discussion discussion, Long id) {
        getById(id);
        discussion.setId(id);
        return discussionRepository.save(discussion);
    }
    public Discussion delete(Long id) {
        Discussion discussion = getById(id);
        discussionRepository.delete(discussion);
        return discussion;
    }
}
