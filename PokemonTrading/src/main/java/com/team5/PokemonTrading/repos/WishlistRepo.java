package com.team5.PokemonTrading.repos;

import com.team5.PokemonTrading.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

}
