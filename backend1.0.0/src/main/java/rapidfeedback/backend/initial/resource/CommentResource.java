package rapidfeedback.backend.initial.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapidfeedback.backend.initial.mapper.CommentMapper;
import rapidfeedback.backend.initial.model.Comment;

import java.util.List;

@RestController
@RequestMapping("/rest/Comment")
public class CommentResource {
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentMapper.findAll();
    }
}
