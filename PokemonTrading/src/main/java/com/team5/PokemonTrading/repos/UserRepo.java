package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {
}
