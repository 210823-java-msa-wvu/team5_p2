package com.team5.PokemonTrading.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import com.team5.PokemonTrading.services.PokemonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deal")

public class DealResource {

    public final DealServices dealServices;
    public final PokemonServices pokemonServices;

    @Autowired
    public DealResource (DealServices dealServices, PokemonServices pokemonServices){
        this.dealServices = dealServices;
        this.pokemonServices = pokemonServices;
    }


    //to get all deals in the market
   // @GetMapping("/all")//not needed yet
    //public ResponseEntity<List<Deal>> getAllDeals () {
      //  List<Deal> deals = dealServices.findAllDeals();
        //return new ResponseEntity<>(deals, HttpStatus.OK);
   // }


    //to add new item to the market
    @PostMapping(value = "/sell",consumes = "application/json")
    public ResponseEntity<Deal> addDeal(@RequestBody Map<String,String> json,
                                        @CookieValue("userinfo") String userinfo) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User u = om.readValue(userinfo,User.class);
        Deal d = new Deal();
        d.setDescription(json.get("description"));
        d.setSeller(u);
        d.setType(Integer.parseInt(json.get("type")));
        d.setExpireDate(LocalDate.parse(json.get("expire_date")));
        d.setPokeId(pokemonServices.findPokemonById(Integer.parseInt(json.get("pokeid"))));
        //is a trade
        if(d.getType()==3){
            d.setTradeFor(pokemonServices.findPokemonById(Integer.parseInt(json.get("trade_for"))));
        }
        //is a buy now or auction
        else{
            d.setPrice(Float.parseFloat(json.get("price")));
        }
        Deal newDeal = dealServices.addDeal(d);
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
