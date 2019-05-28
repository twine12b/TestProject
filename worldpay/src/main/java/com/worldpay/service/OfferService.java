/**
 * Offer CRUD interface
 * 
 * @Author Richard renaud
 */
package com.worldpay.service;

import java.util.List;

import com.worldpay.domain.Offer;

public interface OfferService {
	
	public List<Offer> getAllOffers();
	
	public Offer getOfferById(Integer id);

	public Offer addOffer(Offer o);
	
	public void deleteOffer(Offer o);
	
	public boolean doExpire(Offer o);

}
