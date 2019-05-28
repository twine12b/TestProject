/*
 * 
 */
package com.worldpay.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worldpay.domain.Offer;
import com.worldpay.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	private OfferRepository offerRepository;

	/**
	 * Find all Offers
	 * @return List<Offer>
	 */
	public List<Offer> getAllOffers() {
		List<Offer> offers = new ArrayList<>();
		offerRepository.findAll()
			.forEach(offers::add);	
		return offers;
	}

	/**
	 * Find offer by id
	 * @param id
	 * @return Offer
	 */
	public Offer getOfferById(Integer id) {
		return offerRepository.findById(id).get();
	}

	/**
	 * Add Offer
	 */
	public Offer addOffer(Offer o) {
		return offerRepository.save(o);

	}

	/**
	 * Delete Offer
	 */
	public void deleteOffer(Offer o) {
		offerRepository.delete(o);
	}

	/**
	 * update hasExpired 
	 * return true if NOT expired
	 */
	public boolean doExpire(Offer o) {
		Calendar now = Calendar.getInstance();
			if(o.getExpire().before(now)) {
//				offerRepository.delete(o);
				o.setHasExpired(true);
				offerRepository.save(o);
				return false;
			}
		return true;
	}

}
