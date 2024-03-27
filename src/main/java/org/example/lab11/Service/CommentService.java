package org.example.lab11.Service;

import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Comment;
import org.example.lab11.Model.Post;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.CommentRepository;
import org.example.lab11.Repository.PostRepository;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }
    public void addComment(Comment comment){
        User u=userRepository.findUserByUser_id(comment.getUser_id());
        Post p=postRepository.findPostById(comment.getPost_id());
        if (u==null || p==null){
            throw new RuntimeException("not found user id or post id");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment){
        Comment c=commentRepository.findCommentById(id);
        if (c==null){
            throw new ApiException("not found id");
        }
    c.setPost_id(comment.getPost_id());
        c.setComment_date(comment.getComment_date());
        c.setContent(comment.getContent());
        c.setUser_id(comment.getUser_id());
        commentRepository.save(c);
    }

    public void deleteComment(Integer id){
        Comment c=commentRepository.findCommentById(id);
        if (c==null){
            throw new ApiException("not found id");
        }
        commentRepository.delete(c);
    }

    //---------------- 2\search comments by user Id
    public List<Comment> commentsByUserId(Integer id){
        List<Comment> list=commentRepository.findCommentByUser_id(id);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }

    //---------------- 3\search comments by Post Id

    public List<Comment> commentsByPostId(Integer postId){
        List<Comment> comments=commentRepository.findCommentsByPost_id(postId);
        if (comments.isEmpty()){
            throw new ApiException("not found result");
        }
        return comments;
    }

    //    10-      extra endpoint
    //search by content words

    public List<Comment> searchByWord(String content){
        List<Comment> list=commentRepository.findCommentsByContentContainsIgnoreCase(content);
        if (list.isEmpty()){
            throw new ApiException("not found ");
        }
        return list;
    }
}
