package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.exceptions.PokemonNotFoundException;
import com.team5.PokemonTrading.exceptions.UserNotFoundException;
import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.repos.PokemonRepo;
import com.team5.PokemonTrading.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service

public class UserServices {


    private final UserRepo userRepo;

    @Autowired
    public UserServices(UserRepo userRepo) {this.userRepo = userRepo;}

    public User addUser(User user) {return userRepo.save(user);}

    public List<User> findAllUsers() {return userRepo.findAll();}

    public User updateUser(User user) {return userRepo.save(user);}

    public User findUserById(Integer id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    //add findUserByUsername
    public User findUserByUsername (String username){
        return userRepo.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username" + username + "not found"));
    }

    public void deleteUser(Integer id){
        userRepo.deleteUserById(id);
    }

}
