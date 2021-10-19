package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.models.Wishlist;
import com.team5.PokemonTrading.repos.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WishlistServices {
    private final WishlistRepo wishlistRepo;

    @Autowired
    public WishlistServices(WishlistRepo wishlistRepo){ this.wishlistRepo = wishlistRepo;}

    public Wishlist addWishlist(Wishlist wishlist){ return wishlistRepo.save(wishlist);}

    public void deleteWishlist(Integer id){ wishlistRepo.deleteById(id);}

    public List<Wishlist> viewMyWishlist(User user){
        List<Wishlist> wishlists = wishlistRepo.findAll();
        List<Wishlist> myWishlists = new ArrayList<>();

        for(Wishlist w : wishlists) {
            if(w.getUserid().getId() == user.getId())
                myWishlists.add(w);
        }
        return myWishlists;
    }


    public List<Wishlist> viewMyWishlist(User user){
        List<Wishlist> wishlists = wishlistRepo.findAll();
        List<Wishlist> myWishlists = new ArrayList<>();

        for(Wishlist w : wishlists) {
            if(w.getUserid().getId() == user.getId())
               myWishlists.add(w);
        }
        return myWishlists;
    }



}
