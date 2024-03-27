package org.example.lab11.Service;

import lombok.AllArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.Post;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User u=userRepository.findUserByUser_id(id);
        if (u==null){
            throw new ApiException("not found id");
        }
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        u.setRegistration_date(user.getRegistration_date());
        userRepository.save(u);
    }

    public void deleteUser(Integer id){
        User u=userRepository.findUserByUser_id(id);
        if (u==null){
            throw new ApiException("not found id");
        }
        userRepository.delete(u);
    }
    //-------------------------7\search users by register Date
    public List<User> listByDate(LocalDate date){
        List<User> list=userRepository.findUserByRegistration_date(date);
        if (list.isEmpty()){
            throw new ApiException("not found result");
        }
        return list;
    }

    //-----------------8\ search users  by username
    public User searchByUsername(String name){
        User u=userRepository.findUserByUsername(name);
        if (u==null){
            throw new ApiException("Not found result");
        }
        return u;
    }
}
