package org.example.lab11.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiResponse;
import org.example.lab11.Model.Comment;
import org.example.lab11.Model.Post;
import org.example.lab11.Service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @GetMapping("/get")
    public ResponseEntity getAllComment(){
        return ResponseEntity.status(200).body(commentService.getAllComment());
    }


    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);}

            commentService.addComment(comment);
            return ResponseEntity.status(200).body(new ApiResponse("comment added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@RequestBody @Valid Comment comment ,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(id, comment);
        return  ResponseEntity.status(200).body(new ApiResponse("comment updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id ){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("comment deleted"));
    }

    @GetMapping("/search-user/{id}")
    public ResponseEntity searchByUserId(@PathVariable Integer id){
        List<Comment> list_user=commentService.commentsByUserId(id);
        return  ResponseEntity.status(200).body(list_user);
    }
    @GetMapping("/search-comm/{post_id}")
    public ResponseEntity searchByPostId(@PathVariable Integer post_id){
        List<Comment> comments=commentService.commentsByPostId(post_id);
        return  ResponseEntity.status(200).body(comments);
    }

}
