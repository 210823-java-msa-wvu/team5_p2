package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.Wishlist;
import com.team5.PokemonTrading.repos.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WishlistServices {
    private final WishlistRepo wishlistRepo;

    @Autowired
    public WishlistServices(WishlistRepo wishlistRepo){ this.wishlistRepo = wishlistRepo;}

    public Wishlist addWishlist(Wishlist wishlist){ return wishlistRepo.save(wishlist);}

    public void deleteWishlist(Integer id){ wishlistRepo.deleteById(id);}



}
