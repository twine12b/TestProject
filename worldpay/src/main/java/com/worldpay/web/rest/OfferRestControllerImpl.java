package com.worldpay.web.rest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worldpay.domain.Offer;
import com.worldpay.helper.Error;
import com.worldpay.service.OfferServiceImpl;

@RestController("OfferController")
public class OfferRestControllerImpl implements OfferRestController {
	
	@Autowired 
	OfferServiceImpl offerService;
	
	// Custom error messages
	private final String err404 = "OfferNotFound";
	private final String err400 = "InvalidArgument";
	private final String err400DuplicateName = "DuplicateOfferName";
	private final String err204 = "NoOffersFound";
	private final String successDeleted = "OfferDeleted";
	private final String errDeleted = "DeletionHasError";
	private final String expired = "OfferHasExpired";
	private final String cancel = "OfferIsCancelled";

	/**
	 * Get all Offers
	 */
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Offer>> getAllOffers() {
		List<Offer> offers = offerService.getAllOffers();
		
		// remove expired offers from list
		for(int x = 0; x < offers.size(); x++) {
			if (offerService.doExpire(offers.get(x)) == false) {
				offers.remove(x);
			}	
		}
		
		  if(offers.size() == 0){
			   return new ResponseEntity<List<Offer>>(HttpStatus.NO_CONTENT);
			  } else {
			  return new ResponseEntity<List<Offer>>(offers, HttpStatus.OK);
			  }
	}

	/**
	 * Get Offer by id
	 */	
	@GetMapping(value = "/offers/{id}")
	@ResponseBody
	public ResponseEntity<?> getOfferById(@PathVariable Integer id) {
		Error response = new Error();
		ResponseEntity<?> re = new ResponseEntity<>(HttpStatus.FOUND); //initialize response entity [not null]
		Offer offer = offerService.getOfferById(id);
		
		if(offerService.doExpire(offer) == true) {
			try {
			
				// Check if realm exists
				if(offer !=null) { 
					re = re.ok(offer);
				}
			} catch(NoSuchElementException e) {  
				response.setCode(err404);
				re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}	
		} else {
			response.setCode(expired);
			re = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		
		return re;
	}
		
		/**
		 * Create new Offer
		 */
		@PostMapping("offers/create")
		@ResponseBody
		public ResponseEntity<?> createOffer(@Valid @RequestBody Offer o) {

			Offer offer = offerService.addOffer(o);			
			return new ResponseEntity<>(offer, HttpStatus.CREATED); 
			
		}

		/**
		 * Delete Offer
		 */
		@DeleteMapping("offers/delete/{id}")
		public ResponseEntity<?> deleteOffer(@PathVariable Integer id) {
			Offer o = offerService.getOfferById(id);
			Error response = new Error();
			ResponseEntity<?> re = new ResponseEntity<>(HttpStatus.FOUND); //initialize response entity [not null]
			
			try { 
				if(o != null) {
					response.setCode(successDeleted);
					re = ResponseEntity.status(HttpStatus.OK).body(response);
					offerService.deleteOffer(o);
				}
			} catch(NoSuchElementException e) {
				response.setCode(errDeleted);
				re = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
			return re;
		}

		/**
		 * Cancel Offer
		 */
		@DeleteMapping("offers/cancel/{id}")
		public ResponseEntity<?> cancelOffer(@PathVariable Integer id) {
			Offer o = offerService.getOfferById(id);
			Error response = new Error();
			ResponseEntity<?> re = new ResponseEntity<>(HttpStatus.FOUND); //initialize response entity [not null]
			
			try { 
				if(o != null && o.isHasExpired() == false) {
					response.setCode(cancel);
					re = ResponseEntity.status(HttpStatus.OK).body(response);
					offerService.deleteOffer(o);
				}
			} catch(NoSuchElementException e) {
				response.setCode(errDeleted);
				re = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			
			return re;
		}


}
