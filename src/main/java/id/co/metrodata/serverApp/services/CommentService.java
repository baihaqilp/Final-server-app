package id.co.metrodata.serverApp.services;

import id.co.metrodata.serverApp.models.Comment;
import id.co.metrodata.serverApp.models.dto.CommentRequest;
import id.co.metrodata.serverApp.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private DiscussionService discussionService;
    private EmployeeService employeeService;

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment getById(Long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not Found!"));
    }

    public Comment create(CommentRequest commentRequest) {
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setDiscussion(discussionService.getById(commentRequest.getDiscussionId()));
        comment.setEmployee(employeeService.getById(commentRequest.getEmployeeId()));
        return commentRepository.save(comment);
    }

    public Comment update(Long id, CommentRequest commentRequest) {
        getById(id);
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setDiscussion(discussionService.getById(commentRequest.getDiscussionId()));
        comment.setEmployee(employeeService.getById(commentRequest.getEmployeeId()));
        comment.setId(id);
        return commentRepository.save(comment);
    }

    public Comment delete(Long id) {
        Comment comment = getById(id);
        commentRepository.delete(comment);
        return comment;
    }
}
