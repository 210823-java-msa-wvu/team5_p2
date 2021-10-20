package com.team5.PokemonTrading.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deal")

public class DealResource {

    public final DealServices dealServices;

    public DealResource (DealServices dealServices){this.dealServices = dealServices;}

    //to add new item to the market
    @PostMapping("/sell")
    public ResponseEntity<Deal> addDeal(@RequestBody Deal deal) {
        Deal newDeal = dealServices.addDeal(deal);
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
        if(deal.getSeller().getId().equals(user.getId())) {
            dealServices.deleteDeal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
