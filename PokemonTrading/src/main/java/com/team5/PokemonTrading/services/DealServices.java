package com.team5.PokemonTrading.services;

import com.team5.PokemonTrading.models.Deal;
import com.team5.PokemonTrading.repos.DealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealServices {
    private final DealRepo dealRepo;

    @Autowired
    public DealServices(DealRepo dealRepo) {
        this.dealRepo = dealRepo;
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
}
