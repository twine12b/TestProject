package com.worldpay.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.worldpay.domain.Offer;

public interface OfferRestController {
	
	public ResponseEntity <List<Offer>> getAllOffers();
	
	public ResponseEntity<?> getOfferById(Integer id);
	
	public ResponseEntity<?> createOffer(Offer o);
	
	public ResponseEntity<?> deleteOffer(Integer id);
	
	public ResponseEntity<?> cancelOffer(Integer id);

}
