package org.example.lab11.Repository;

import org.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
Post findPostById(Integer id);

@Query("select p from Post p where p.publish_date=?1")
List<Post> findPostsByPublish_date(LocalDate date);

    @Query("select p from Post p where p.user_id=?1")
List<Post> findPostByUser_id(Integer id);

    @Query("select  u from Post u where u.publish_date=?1 and u.title=?2")
    List<Post> findByPublish_dateAndTitle(LocalDate date,String title);


//    @Query("select p from Post p where p.category_id=?1")
//Post searchPostByCategoryId(Integer id);



}
