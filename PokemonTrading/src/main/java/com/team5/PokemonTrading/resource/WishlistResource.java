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
import java.util.Map;

@RestController
@RequestMapping("/wishlist")
public class WishlistResource {
    private final WishlistServices wishlistServices;

    public WishlistResource(WishlistServices wishlistServices) {
        this.wishlistServices = wishlistServices;
    }

    //use cookie and allow user to only send pokeid
    @PostMapping("/add")
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
        Wishlist newWishlist = wishlistServices.addWishlist(wishlist);
        return new ResponseEntity<>(newWishlist, HttpStatus.CREATED);
    }

    @PostMapping(value = "/view",consumes = "application/json")
    public ResponseEntity<List<Wishlist>> viewMyWishlist(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String userinfo = json.get("userinfo");
        User user = om.readValue(userinfo, User.class);
        List<Wishlist> myItems = wishlistServices.viewMyWishlist(user.getId());
        return new ResponseEntity<>(myItems, HttpStatus.OK);
    }

    @PostMapping("/delete/delete/{id}")
    public ResponseEntity<?> deleteWishlist(@PathVariable("id") Integer id,
                                            @RequestBody Map<String, String> json) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        String userinfo = json.get("userinfo");
        User user = om.readValue(userinfo,User.class);
        Wishlist wl = wishlistServices.getById(id);
        //check to see if the wishlist owner matches cookie holder
        if(wl.getUserid().getId().equals(user.getId())
                && wl.getUserid().getPassword().equals((user.getPassword()))) {
            wishlistServices.deleteWishlist(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
