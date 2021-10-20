package com.team5.PokemonTrading.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.PokemonTrading.exceptions.UserNotFoundException;
import com.team5.PokemonTrading.models.User;
import com.team5.PokemonTrading.services.DealServices;
import com.team5.PokemonTrading.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/front")
public class FrontResource {
    private final UserServices userServices;
    private final DealServices dealServices;

    @Autowired
    public FrontResource(UserServices us,DealServices ds){
        userServices=us;
        dealServices=ds;
    }

    //json web token
    @PostMapping(path = "/login",consumes = "application/json")
    public ResponseEntity<?> userLogin(@RequestBody Map<String,String> json, HttpServletResponse resp) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String username = json.get("username");
        String password = json.get("password");
        try {
            User u = userServices.findUserByUsername(username);
            if(!u.getPassword().equals(password)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            else{
                dealServices.updateDeals();
                Cookie cookie = new Cookie("userinfo",om.writeValueAsString(u));
                resp.addCookie(cookie);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path="/create",consumes="application/json")
    public ResponseEntity<?> createAccount(@RequestBody Map<String,String>json){
        String username = json.get("username");
        String password = json.get("password");
        try {
            User u = userServices.findUserByUsername(username);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch(UserNotFoundException e){
            User new_u = new User();
            new_u.setUsername(username);
            new_u.setPassword(password);
            //new users get 5 points for free
            new_u.setBalance(5f);
            userServices.addUser(new_u);
            //ask this one, whether return no content or return the user object back
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
