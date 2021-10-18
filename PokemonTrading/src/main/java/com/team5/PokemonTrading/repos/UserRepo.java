package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    void deleteUserById(Integer id);

    Optional<User> findUserById(Integer id);
    Optional<User> findUserByUsername(String username);
}
