package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.models.Transaction;
import com.team5.PokemonTrading.repos.DealRepo;
import com.team5.PokemonTrading.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealServices {
    private final DealRepo dealRepo;
    private final TransactionRepo transactionRepo;

    @Autowired
    public DealServices(DealRepo dealRepo, TransactionRepo transactionRepo) {
        this.dealRepo = dealRepo;
        this.transactionRepo = transactionRepo;
    }

    public Deal addDeal(Deal d) {
        return dealRepo.save(d);
    }

    public List<Deal> findAllDeals() {
        return dealRepo.findAll();
    }

    public List<Deal> findDealsBySeller(int id) {
        List<Deal> deals = dealRepo.findAll();
        List<Deal> result = new ArrayList<>();

        for(Deal d : deals) {
            if(d.getSeller().getId() == id)
                result.add(d);
        }
        return result;
    }

    public Deal updateDeal(Deal d) {
        return dealRepo.save(d);
    }

    public Deal findById(Integer id) {
        Optional<Deal> od = dealRepo.findById(id);
        if(od.isPresent()) return od.get();
        else return null;
    }

    public void deleteDeal(Integer id){
        dealRepo.deleteById(id);
    }

    public void updateDeals(){
        List<Deal> deals = dealRepo.findAll();
        for(Deal d:deals){
            LocalDate expire = d.getExpireDate();
            LocalDate today = LocalDate.now();
            //deal has passed expiration date
            if(today.compareTo(expire)>=0){
                Transaction t = new Transaction();
                t.setDescription(d.getDescription());
                t.setCompleteDate(today);
                t.setSeller(d.getSeller());
                t.setPokeId(d.getPokeId());
                t.setPrice(d.getPrice());
                t.setTradeFor(d.getTradeFor());
                t.setBuyer(null);
                t.setType(d.getType());
                t.setStatus(0);
                //remove from deal and add to transaction
                transactionRepo.save(t);
                dealRepo.deleteById(d.getId());
            }
        }
    }
}