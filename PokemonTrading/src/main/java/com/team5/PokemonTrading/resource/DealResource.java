package com.team5.PokemonTrading.resource;


import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deal")

public class DealResource {

    public final DealServices dealServices;

    public DealResource (DealServices dealServices){this.dealServices = dealServices;}


    //to get all deals in the market
   // @GetMapping("/all")//not needed yet
    //public ResponseEntity<List<Deal>> getAllDeals () {
      //  List<Deal> deals = dealServices.findAllDeals();
        //return new ResponseEntity<>(deals, HttpStatus.OK);
   // }


    //to add new item to the market
    @PostMapping("/sell")
    public ResponseEntity<Deal> addDeal(@RequestBody Deal deal) {
        Deal newDeal = dealServices.addDeal(deal);
        return new ResponseEntity<>(newDeal, HttpStatus.CREATED);
    }

    //to update market item
   // @PutMapping("/update")//not need it yet
    //public ResponseEntity<Deal> updateDeal(@RequestBody Deal deal) {
      //  Deal updateDeal = dealServices.updateDeal(deal);
        //return new ResponseEntity<>(updateDeal, HttpStatus.OK);
   // }

    //to delete/remove market item by id //thank you Ton for taking it from here.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteDealById(@CookieValue("userinfo") String userinfo,
            {

        //access cookie storing user information, find out
        //in that cookie will show user id
        //user id in the cookie  must match deal seller id to make sure is the correct one
        // if they match, you delete deal item
        //if not return http status forbideen.
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }





}
