package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {
    void deletePokemonById(Integer id);

    Optional<Pokemon> findPokemonById(Integer id);
    Optional<Pokemon> findPokemonByName(String name);
    public List<Pokemon> findAllByOrderByName();
}
