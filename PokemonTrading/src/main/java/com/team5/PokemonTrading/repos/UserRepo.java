package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
