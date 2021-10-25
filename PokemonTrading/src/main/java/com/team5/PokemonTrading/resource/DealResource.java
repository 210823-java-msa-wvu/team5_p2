package com.team5.PokemonTrading.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Transaction;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import com.team5.PokemonTrading.services.PokemonServices;
import com.team5.PokemonTrading.services.TransactionServices;
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

    private final DealServices dealServices;
    private final PokemonServices pokemonServices;
    private final TransactionServices transactionServices;


    @Autowired
    public DealResource(DealServices dealServices, PokemonServices pokemonServices, TransactionServices transactionServices){
        this.dealServices = dealServices;
        this.pokemonServices = pokemonServices;
        this.transactionServices = transactionServices;
    }

    @GetMapping("/see")
    public ResponseEntity<List<Deal>> getAllDeal(){
        List<Deal> lod = dealServices.findAllDeals();
        return new ResponseEntity<>(lod, HttpStatus.OK);
    }

    //to add new item to the market
    @PostMapping(value = "/sell",consumes = "application/json")
    public ResponseEntity<Deal> addDeal(@RequestBody Map<String,String> json,
                                        @CookieValue("userinfo") String userinfo) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User u = om.readValue(userinfo,User.class);
        Deal d = new Deal();
        System.out.println(u);
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
        System.out.println(d);
        Deal newDeal = dealServices.addDeal(d);
        return new ResponseEntity<>(newDeal, HttpStatus.CREATED);
    }

    //to delete/remove market item by id //thank you Ton for taking it from here.
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMyDeal(@PathVariable("id") Integer id,
                                            @CookieValue("userinfo") String userinfo) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(userinfo,User.class);
        Deal deal = dealServices.findById(id);
        //check to see if the wishlist owner matches cookie holder
        //URGENT: once deal is deleted, a transaction of failure status should be added
        if(deal.getSeller().getId().equals(user.getId())) {
            dealServices.deleteDeal(id);

            //Add a new transaction with "cancel" status
            Transaction newTransaction = new Transaction(deal.getType(), null, deal.getSeller(), LocalDate.now(), deal.getPrice(), deal.getTradeFor(), deal.getPokeId(), deal.getDescription(), 0);
            transactionServices.addTransaction(newTransaction);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
