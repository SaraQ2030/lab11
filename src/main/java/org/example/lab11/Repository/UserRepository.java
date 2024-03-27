package org.example.lab11.Repository;

import org.example.lab11.Model.Post;
import org.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
@Query("select u from User u where u.id=?1")
  User findUserByUser_id(Integer id);

  @Query("select p from User p where p.registration_date=?1")
  List<User> findUserByRegistration_date(LocalDate date);

  User findUserByUsername(String name);


}
