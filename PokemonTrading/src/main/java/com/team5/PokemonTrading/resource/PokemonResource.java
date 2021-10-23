package com.team5.PokemonTrading.resource;

import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.services.PokemonServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonResource {
    private final PokemonServices pokemonServices;

    public PokemonResource(PokemonServices pokemonServices) {
        this.pokemonServices = pokemonServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> getAllPokemons () {
        List<Pokemon> pokemons = pokemonServices.findAllPokemons();
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Pokemon> getPokemonById (@PathVariable("id") Integer id) {
        Pokemon pokemon = pokemonServices.findPokemonById(id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pokemon> getPokemonByName (@PathVariable("name") String name) {
        Pokemon pokemon = pokemonServices.findPokemonByName(name);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Pokemon> addPokemon(@RequestBody Pokemon pokemon) {
        Pokemon newPokemon = pokemonServices.addPokemon(pokemon);
        return new ResponseEntity<>(newPokemon, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon) {
        Pokemon updatePokemon = pokemonServices.updatePokemon(pokemon);
        return new ResponseEntity<>(updatePokemon, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePokemon(@PathVariable("id") Integer id) {
        pokemonServices.deletePokemon(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
