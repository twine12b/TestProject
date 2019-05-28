package com.worldpay.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.worldpay.domain.Offer;
import com.worldpay.helper.ObjectConverter;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OfferServiceUnitTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	OfferServiceImpl offerService;
	
	private Offer offer = new Offer();
	
	private ObjectConverter converter;
	
	/**
	 * initialise a Offer
	 */
	private Offer initOffer() {
		Integer id = 1;
		Offer myOffer = new Offer();
		Calendar calendar = new GregorianCalendar(2019,0,31);
		Calendar cal = calendar.getInstance();

		myOffer.setId(id);
		myOffer.setName("name"+id);
		myOffer.setDescription("description"+id);
		myOffer.setCurrencyCode("currencyCode"+id);
		myOffer.setPrice(9.99+id);
		      
		      cal.add(Calendar.MONTH, id);
		      myOffer.setExpire(cal);
		      
		return myOffer;
	
	}
	
	@Before
	public void startUp() {
		offer = initOffer();
	}

	@Test
	public void testGetAllOffers() throws Exception {
		
			// Mock OfferService
			Mockito.when(offerService.getAllOffers()).thenReturn(
					Collections.emptyList()
			);
			
			// url request
			MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/offers")
				.accept(MediaType.APPLICATION_JSON)
		    ).andReturn();
			
			System.out.println(mvcResult.getResponse());
			
			Mockito.verify(offerService).getAllOffers();
	}

	@Test
	public void testGetOfferById() throws Exception {
		Integer id = 1;

		// Mock OfferService
		Mockito.when(offerService.getOfferById(id)).thenReturn(
				offer
		);
		
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/"+id)
			.accept(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(offerService).getOfferById(id);
	}
	
//	@Test
	public void testAddOffer() throws Exception {
	
		offer.setId(3); //4th item in list
		
		System.out.println("*******" + converter.toJson(offer));
		
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/offers/create")
			.accept(MediaType.APPLICATION_JSON)
			.content(converter.toJson(offer))
			.contentType(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(offerService).addOffer(offer);
	}

}
