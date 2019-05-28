package com.worldpay.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.worldpay.domain.Offer;
import com.worldpay.service.OfferServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class OfferRestControllerUnitTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	OfferServiceImpl offerService;
	
	private List<Offer> lst = new ArrayList<Offer>();
	
	private String offer = "";
	
	@SuppressWarnings("static-access")
	@Before
	public void startup() {
		// Mock DB return of 3 Offers
		Calendar calendar = new GregorianCalendar(2019,0,31);
		Calendar cal = calendar.getInstance();
		Offer o = new Offer();
		for(int x=0; x <3; x++) {
			o.setId(x);
			 o.setName("name"+x);
			  o.setDescription("description"+x);
			     o.setCurrencyCode("currencyCode"+x);
			      o.setPrice(9.99+x);
			      o.setHasExpired(false);
			      cal.add(Calendar.MONTH, x);
			       o.setExpire(cal);
			       
			       lst.add(x, o);
		}
		
		this.offer = "{\"id\":4,\"name\":\"name4\",\"description\":\"description4\",\"currencyCode\":\"uk4\",\"price\":10.99,\"expire\":\"2019-07-11T23:00:00.000+0000\",\"hasExpired\":false}";
	}
	

	@Test
	public void testGetAllOffers() throws Exception {
		
			// Mock OfferService
			Mockito.when(offerService.getAllOffers()).thenReturn(
					lst
			);
			
			// url request
			MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/offers")
				.accept(MediaType.APPLICATION_JSON)
		    )
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			
			System.out.println(mvcResult.getResponse());
			
			Mockito.verify(offerService).getAllOffers();
	}

	@Test
	public void testGetOfferById() throws Exception {
		Integer id = 2;
		// Mock OfferService
		Mockito.when(offerService.getOfferById(id)).thenReturn(
				lst.get(id)
		);
		
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/"+id)
			.accept(MediaType.APPLICATION_JSON)
	    )
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(offerService).getOfferById(id);
	}
	
//	@Test
	public void testAddOffer() throws Exception {
		Integer id = 4;
		Offer myOffer = lst.get(0);
		 myOffer.setId(id);
		
		Mockito.when(offerService.addOffer(myOffer)).thenReturn(
				myOffer
		);
		
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.post("/offers/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(offer)
	    )
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		Mockito.verify(offerService).addOffer(myOffer);
	}

}
