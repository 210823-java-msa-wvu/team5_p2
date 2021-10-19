package com.team5.PokemonTrading.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Transaction;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import com.team5.PokemonTrading.services.TransactionServices;
import com.team5.PokemonTrading.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserResource {
    private final UserServices userServices;
    private final DealServices dealServices;
    private final TransactionServices transactionServices;

    public UserResource(UserServices userServices, DealServices dealServices, TransactionServices transactionServices) {
        this.userServices = userServices;
        this.dealServices = dealServices;
        this.transactionServices = transactionServices;
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

    @PostMapping("/buy/{id}")
    public ResponseEntity<?> processDeal(@PathVariable("id") Integer id, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String, Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }

        Cookie getBalance = cookieMap.get("balance");
        Deal currentDeal = dealServices.findById(id);
        User currentUser = userServices.findUserById(id);
        if (currentDeal.getPrice() < Float.parseFloat(getBalance.getValue())) {
            Transaction newTransaction = new Transaction(currentDeal.getType(), currentUser, currentDeal.getSeller(), LocalDate.now(), currentDeal.getPrice(), currentDeal.getTradeFor(), currentDeal.getPokeId(), currentDeal.getDescription(), 1);
            transactionServices.addTransaction(newTransaction);
            dealServices.deleteDeal(id);
            // Still waiting for Sergio create new updateBalance() method in order to use it to update the balance for seller and buyer
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }
    
    //front end will send in a put request with request body of a json of form {"amount":"-399.99"}
    @PutMapping(value = "/load",consumes = "application/json")
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
            resp.addCookie(cookie);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
