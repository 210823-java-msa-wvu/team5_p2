package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.exceptions.PokemonNotFoundException;
import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.repos.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PokemonServices {
    private final PokemonRepo pokemonRepo;

    @Autowired
    public PokemonServices(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public Pokemon addPokemon(Pokemon pokemon) {
        return pokemonRepo.save(pokemon);
    }

    public List<Pokemon> findAllPokemons() {
        return pokemonRepo.findAll();
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
        return pokemonRepo.save(pokemon);
    }

    public Pokemon findPokemonById(Integer id) {
        return pokemonRepo.findPokemonById(id)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon by id " + id + " was not found"));
    }

    public void deletePokemon(Integer id){
        pokemonRepo.deletePokemonById(id);
    }
}
