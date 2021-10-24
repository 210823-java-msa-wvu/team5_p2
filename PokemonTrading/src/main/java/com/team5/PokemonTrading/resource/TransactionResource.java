package com.team5.PokemonTrading.resource;

import com.team5.PokemonTrading.models.Pokemon;
import com.team5.PokemonTrading.models.Transaction;
import com.team5.PokemonTrading.services.TransactionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {
    private final TransactionServices transactionServices;

    public TransactionResource(TransactionServices transactionServices) {
        this.transactionServices = transactionServices;
    }

    @GetMapping("/buy/{id}")
    public ResponseEntity<List<Transaction>> getBuyHistoryByBuyerId (@PathVariable("id") Integer id) {
        List<Transaction> transactions = transactionServices.findAllTransactionsByBuyerId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/sell/{id}")
    public ResponseEntity<List<Transaction>> getSellHistoryBySellerId (@PathVariable("id") Integer id) {
        List<Transaction> transactions = transactionServices.findAllTransactionsBySellerId(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/findtransactions/{id}")
    public ResponseEntity<List<Transaction>> getUserHistory (@PathVariable("id") Integer id) {
        List<Transaction> transactions = transactionServices.findAllByBuyerOrSeller(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
