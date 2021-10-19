package com.team5.PokemonTrading.resource;

import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.services.DealServices;
import com.team5.PokemonTrading.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserServices userServices;
    private final DealServices dealServices;

    public UserResource(UserServices userServices, DealServices dealServices) {
        this.userServices = userServices;
        this.dealServices = dealServices;
    }

    @GetMapping("/sell/{id}")
    public ResponseEntity<List<Deal>> getMyCurrentSells (@PathVariable("id") Integer id) {
        List<Deal> deals = dealServices.findDealsBySeller(id);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @PostMapping("/sell/add")
    public ResponseEntity<Deal> addNewSell(@RequestBody Deal deal) {
        Deal newDeal = dealServices.addDeal(deal);
        return new ResponseEntity<>(newDeal, HttpStatus.CREATED);
    }


}
