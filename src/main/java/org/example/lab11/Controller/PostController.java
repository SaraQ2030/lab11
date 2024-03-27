package org.example.lab11.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiResponse;
import org.example.lab11.Model.Post;
import org.example.lab11.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/blog/post")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.status(200).body(postService.getAllPost());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
            return ResponseEntity.status(200).body(new ApiResponse("Post added"));


            }
            @PutMapping("/update/{id}")
            public ResponseEntity updatePost(@PathVariable Integer id,@RequestBody @Valid Post post,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
       postService.updatePost(id, post);
        return  ResponseEntity.status(200).body(new ApiResponse("Post updated"));
            }
            @DeleteMapping("/delete/{id}")
            public ResponseEntity deletePost(@PathVariable Integer id ){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted"));
            }
@GetMapping("/search/{date}")
            public ResponseEntity listByDate(@PathVariable LocalDate date){
                List<Post> list=postService.listByDate(date);
                return ResponseEntity.status(200).body(list);

            }

    @GetMapping("/search-user/{id}")
    public ResponseEntity searchByUserId(@PathVariable Integer id){
          List<Post> list_user=postService.postsByUserId(id);
        return  ResponseEntity.status(200).body(list_user);
    }


    @GetMapping("/search-user-dt/{date}/{title}")
    public ResponseEntity searchByDateTitle(@PathVariable LocalDate date,@PathVariable String title){
        List<Post> list_user=postService.postsByDateTitle(date,title);
        return  ResponseEntity.status(200).body(list_user);
    }



}
