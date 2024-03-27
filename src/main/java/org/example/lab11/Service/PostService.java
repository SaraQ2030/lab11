package org.example.lab11.Service;

import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Post;
import org.example.lab11.Repository.CategoryRepository;
import org.example.lab11.Repository.PostRepository;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }
    public void addPost(Post post){
       if (! userRepository.existsById(post.getUser_id())){
           throw new RuntimeException("not found user id");
                }
        if (!categoryRepository.existsById(post.getCategory_id())){
            throw new RuntimeException("not found category id");
        }
        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post){
        Post p=postRepository.findPostById(id);
        if (p==null){
            throw new ApiException("not found id");
        }
        p.setCategory_id(post.getCategory_id());
        p.setUser_id(post.getUser_id());
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setPublish_date(post.getPublish_date());
        postRepository.save(p);
    }

    public void deletePost(Integer id){
        Post p=postRepository.findPostById(id);
        if (p==null){
            throw new ApiException("not found id");
        }
        postRepository.delete(p);
    }
//-----------------------4\ search posts by publish Date
    public List<Post> listByDate(LocalDate date){
        List<Post> list=postRepository.findPostsByPublish_date(date);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }

//-----------------------5\ search posts by user Id
    public List<Post> postsByUserId(Integer id){
      List<Post> list_posts=postRepository.findPostByUser_id(id);
        if (list_posts.isEmpty()){
            throw new ApiException("not found result");
        }
        return list_posts;
    }

 //-----------------------6\ search posts by date of publish and title

    public List<Post> postsByDateTitle(LocalDate date,String title){
        List<Post> list=postRepository.findByPublish_dateAndTitle(date,title);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }


}
