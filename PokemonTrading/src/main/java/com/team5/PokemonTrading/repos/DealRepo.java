package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepo extends JpaRepository<Deal, Integer> {
}
