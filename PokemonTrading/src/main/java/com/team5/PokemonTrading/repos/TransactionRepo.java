package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
