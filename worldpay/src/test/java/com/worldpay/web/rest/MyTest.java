package com.worldpay.web.rest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 private String offer = "";
	 
	@Before
	public void startup() {
		this.offer = "{\"id\":4,\"name\":\"name4\",\"description\":\"description4\",\"currencyCode\":\"uk4\",\"price\":10.99,\"expire\":\"2019-07-11T23:00:00.000+0000\",\"hasExpired\":false}";
	}

	@Test
	public void shouldContainInitialDBValue() throws Exception {
		this.mockMvc.perform(get("/offer")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("uk0")));
	}
	
	@Test
	public void singleOfferWithId_1() throws Exception {
		this.mockMvc.perform(get("/offer/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("name1")));
	}
	
	@Test
	public void testCreateOffer() throws Exception {
		this.mockMvc.perform(post("/offer/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(offer));
	}
		
	

}
