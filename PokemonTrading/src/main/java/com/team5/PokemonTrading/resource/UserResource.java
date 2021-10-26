package com.team5.PokemonTrading.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.*;
import com.team5.PokemonTrading.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserServices userServices;
    private final DealServices dealServices;
    private final TransactionServices transactionServices;
    private final PokemonServices pokemonServices;
    private final WishlistServices wishlistServices;

    public UserResource(UserServices userServices, DealServices dealServices, TransactionServices transactionServices, PokemonServices pokemonServices, WishlistServices wishlistServices) {
        this.userServices = userServices;
        this.dealServices = dealServices;
        this.transactionServices = transactionServices;
        this.pokemonServices = pokemonServices;
        this.wishlistServices = wishlistServices;
    }

    //can use cookie
    @GetMapping("/mysell/{id}")
    public ResponseEntity<List<Deal>> getMyCurrentSells (@PathVariable("id") Integer id) {
        List<Deal> deals = dealServices.findDealsBySeller(id);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @GetMapping("/notify")
    public ResponseEntity<List<Pokemon>> wishlistNotify (@CookieValue("userinfo") String userinfo) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User u = om.readValue(userinfo,User.class);

        List<Pokemon> pokemons = new ArrayList<>();
        List<Deal> deals = dealServices.findAllDeals();
        List<Wishlist> wishlists = wishlistServices.viewMyWishlist(u.getId());
        for (int i = 0; i < wishlists.size(); i++) {
            for (int x = 0; x < deals.size(); x++) {
                if (deals.get(x).getPokeId() == wishlists.get(i).getPokeid())
                    pokemons.add(pokemonServices.findPokemonById(wishlists.get(i).getPokeid().getId()));
            }
        }
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<?> processDeal(@CookieValue("userinfo") String userinfo,
                                         @PathVariable("id") Integer id,
                                         HttpServletResponse resp) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User u = om.readValue(userinfo,User.class);


        int getUserId = u.getId();
        Deal currentDeal = dealServices.findById(id);
        User currentUser = userServices.findUserById(getUserId);
        float getBalance = currentUser.getBalance();
        System.out.println(u);
        System.out.println(currentUser);
        User seller = userServices.findUserById(currentDeal.getSeller().getId());
        if(currentDeal.getType()!=1) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        if (currentDeal.getPrice() < getBalance) {
            Transaction newTransaction = new Transaction(currentDeal.getType(), currentUser, currentDeal.getSeller(), LocalDate.now(), currentDeal.getPrice(), currentDeal.getTradeFor(), currentDeal.getPokeId(), currentDeal.getDescription(), 1);
            transactionServices.addTransaction(newTransaction);
            dealServices.deleteDeal(id);
            // Update the balance for seller and buyer
            float newBuyerBalance = currentUser.getBalance() - currentDeal.getPrice();
            float newSellerBalance = seller.getBalance() + currentDeal.getPrice();

            //update balance for current user, then send the updated cookie back to the browser
            currentUser.setBalance(newBuyerBalance);
            userServices.updateUser(currentUser);
            Cookie cookie = new Cookie("userinfo",om.writeValueAsString(currentUser));
            cookie.setPath("/");
            resp.addCookie(cookie);

            seller.setBalance(newSellerBalance);
            userServices.updateUser(seller);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/put/bid/{id}", consumes = "application/json")
    public ResponseEntity<?> processAuction(@CookieValue("userinfo") String userinfo,
                                            @PathVariable("id") Integer id,
                                            @RequestBody Map<String, String> json,
                                            HttpServletResponse resp) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        float amount = Float.parseFloat(json.get("amount"));
        User u = om.readValue(userinfo,User.class);
        int getUserId = u.getId();
        Deal currentDeal = dealServices.findById(id);
        User currentUser = userServices.findUserById(getUserId);
        float getBalance = currentUser.getBalance();
        //new price is lower or equal to current price OR submitted price is more than user has OR deal is not auction
        if (currentDeal.getPrice() > amount||amount>currentUser.getBalance()||currentDeal.getType()!=2) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else {
            if(currentDeal.getHighestBidder()==null) {
                //when he is the first bidder, remove his points on hold.
                currentDeal.setHighestBidder(currentUser);
                currentDeal.setPrice(amount);
                currentUser.setBalance(currentUser.getBalance()-amount);
                dealServices.updateDeal(currentDeal);
                userServices.updateUser(currentUser);
                Cookie cookie = new Cookie("userinfo",om.writeValueAsString(currentUser));
                cookie.setPath("/");
                resp.addCookie(cookie);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{
                //when there is a previous bidder, return his points back, and deduction from current bidder.
                User previous_bidder = currentDeal.getHighestBidder();
                float previous_price = currentDeal.getPrice();
                currentDeal.setHighestBidder(currentUser);
                currentDeal.setPrice(amount);
                previous_bidder.setBalance(previous_bidder.getBalance()+previous_price);
                currentUser.setBalance(currentUser.getBalance()-amount);
                dealServices.updateDeal(currentDeal);
                userServices.updateUser(currentUser);
                userServices.updateUser(previous_bidder);
                Cookie cookie = new Cookie("userinfo",om.writeValueAsString(currentUser));
                cookie.setPath("/");
                resp.addCookie(cookie);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
    }

    //front end will send in a put request with request body of a json of form {"amount":"-399.99"}
    @PostMapping(value = "/put/load",consumes = "application/json")
    public ResponseEntity<?> loadBalance(@CookieValue("userinfo") String userinfo,
                                         @RequestBody Map<String, String> json,
                                         HttpServletResponse resp) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        float loadAmount = Float.parseFloat(json.get("amount"));
        //System.out.println(loadAmount);
        //System.out.println(userinfo);
        User u = om.readValue(userinfo,User.class);
        float newAmount = u.getBalance()+loadAmount;
        if(newAmount < 0){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else{
            u.setBalance(newAmount);
            userServices.updateUser(u);
            //save the updated user cookie back to the response body
            Cookie cookie = new Cookie("userinfo",om.writeValueAsString(u));
            cookie.setPath("/");
            resp.addCookie(cookie);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
