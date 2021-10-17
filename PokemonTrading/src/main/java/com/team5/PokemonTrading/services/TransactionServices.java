package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.Transaction;
import com.team5.PokemonTrading.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionServices {
    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionServices(TransactionRepo transactionRepo) {this.transactionRepo = transactionRepo;}

    public Transaction addTransaction(Transaction transaction) {return transactionRepo.save(transaction);}

    public List<Transaction> findAllTransactionsByBuyerId(Integer id) {
        List<Transaction> transactions = transactionRepo.findAll();
        List<Transaction> result = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getBuyer().getId() == id)
                result.add(t);
        }
        return result;
    }

    public List<Transaction> findAllTransactionsBySellerId(Integer id) {
        List<Transaction> transactions = transactionRepo.findAll();
        List<Transaction> result = new ArrayList<>();

        for(Transaction t : transactions) {
            if(t.getBuyer().getId() == id)
                result.add(t);
        }
        return result;
    }

}
