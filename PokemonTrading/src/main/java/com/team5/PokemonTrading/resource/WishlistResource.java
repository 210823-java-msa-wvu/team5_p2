package com.team5.PokemonTrading.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.models.Wishlist;
import com.team5.PokemonTrading.services.WishlistServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/view")
    public ResponseEntity<List<Wishlist>> viewMyWishlist(@CookieValue("userinfo") String userinfo) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(userinfo, User.class);
        List<Wishlist> myItems = wishlistServices.viewMyWishlist(user);
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWishlist(@PathVariable("id") Integer id,
                                            @CookieValue("userinfo") String userinfo) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(userinfo,User.class);
        Wishlist wl = wishlistServices.getById(id);
        //check to see if the wishlist owner matches cookie holder
        if(wl.getUserid().getId().equals(user.getId())) {
            wishlistServices.deleteWishlist(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
