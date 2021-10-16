package com.team5.PokemonTrading.exceptions;

public class PokemonNotFoundException extends RuntimeException{
    public PokemonNotFoundException(String message) {
        super(message);
    }
}
