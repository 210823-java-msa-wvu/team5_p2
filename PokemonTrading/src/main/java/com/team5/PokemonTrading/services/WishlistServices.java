package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.models.Wishlist;
import com.team5.PokemonTrading.repos.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class WishlistServices {
    private final WishlistRepo wishlistRepo;

    @Autowired
    public WishlistServices(WishlistRepo wishlistRepo){ this.wishlistRepo = wishlistRepo;}

    public Wishlist getById(int id){return wishlistRepo.getById(id);}

    public Wishlist addWishlist(Wishlist wishlist){ return wishlistRepo.save(wishlist);}

    public void deleteWishlist(Integer id){ wishlistRepo.deleteById(id);}

    public List<Wishlist> viewMyWishlist(Integer id){
        List<Wishlist> wishlists = wishlistRepo.findAll();
        List<Wishlist> myWishlists = new ArrayList<>();

        for(Wishlist w : wishlists) {
            if(w.getUserid().getId()==id)
                myWishlists.add(w);
        }
        return myWishlists;
    }



}
