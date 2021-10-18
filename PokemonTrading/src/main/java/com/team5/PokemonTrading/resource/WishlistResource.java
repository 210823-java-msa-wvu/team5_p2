package com.team5.PokemonTrading.resource;

import com.team5.PokemonTrading.models.Wishlist;
import com.team5.PokemonTrading.services.WishlistServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistResource {
    private final WishlistServices wishlistServices;

    public WishlistResource(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
        Wishlist newWishlist = wishlistServices.addWishlist(wishlist);
        return new ResponseEntity<>(newWishlist, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWishlist(@PathVariable("id") Integer id) {
        wishlistServices.deleteWishlist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
