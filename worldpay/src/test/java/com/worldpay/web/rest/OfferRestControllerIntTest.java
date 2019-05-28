package com.worldpay.web.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.worldpay.WorldpayApplication;
import com.worldpay.service.OfferServiceImpl;
import com.worldpay.domain.Offer;
import com.worldpay.helper.ObjectConverter;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = WorldpayApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class OfferRestControllerIntTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	OfferServiceImpl offerService;
	
	private String offer = "";

	@Before
	public void setUp() throws Exception {
		this.offer = "{\"id\":4,\"name\":\"name4\",\"description\":\"description4\",\"currencyCode\":\"uk4\",\"price\":10.99,\"expire\":\"2019-07-11T23:00:00.000+0000\",\"hasExpired\":false}";
	}
		
	@Test
	public void testGetAllOffers() throws Exception {
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers")
			.accept(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
		
		assertEquals(0, mvcResult.getResponse().getContentLength());
	}

	@Test
	public void testGetOffersByIdWhenIdNotFound() throws Exception {
		Integer id = 9999;
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/offers/"+id)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse();
	}
	
	@Test
	public void testGetOfferById() throws Exception {
		Integer id = 1;
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/"+id)
			.accept(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
	}
	
	@Test
	public void testCreateOffer() throws Exception {

		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.get("/offers/creates")
			.accept(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
	}
	
	@Test
	public void testDeleteOffer() throws Exception {
		
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.delete("/offers/delete/1")
			.accept(MediaType.APPLICATION_JSON)
			.content(offer)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
	}
	
	@Test
	public void testCancelOffer() throws Exception {
		// url request
		MvcResult mvcResult = mockMvc.perform(
			MockMvcRequestBuilders.delete("/offers/cancel/1")
			.accept(MediaType.APPLICATION_JSON)
	    ).andReturn();
		
		System.out.println(mvcResult.getResponse());
	}

}
