package com.tut.service;

import com.tut.entity.Bags;
import com.tut.repository.BagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagsService {

    @Autowired
    private BagsRepository bagsRepository;
    public List<Bags> getAllBags(){

        return bagsRepository.findAll();
    }

    public void createNewBag(Bags bag){
//
        // Calculate per day bag
        long perdaybag = bag.getShift1() + bag.getShift2() + bag.getShift3() + bag.getShift4();
        bag.setPerdaybag(perdaybag);

        // Get the last record (by date)
        Bags lastBag = bagsRepository.findTopByOrderByDateDesc();

        long previousTotal = (lastBag != null) ? lastBag.getTotalBags() : 0;

        // Calculate totalBags as perdaybag + previous total
        bag.setTotalBags(perdaybag + previousTotal);

        // Save to DB
        bagsRepository.save(bag);
    }
    public void minusBags(Bags bag){
        Bags lastBag = bagsRepository.findTopByOrderByDateDesc();

        long previousTotal = (lastBag != null) ? lastBag.getTotalBags() : 0;
        long minus = bag.getMinus();

        // Calculate remaining after subtracting
        long remaining = previousTotal - minus;
        if (remaining < 0) {
            remaining = 0; // prevent negative
        }

        bag.setRemain(remaining);
        bag.setTotalBags(remaining); // if you want to update the total as well

        bagsRepository.save(bag);
    }


}
