package com.ldeng;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lagoon.controller.UserResource;

@RunWith(SpringJUnit4ClassRunner.class)
public class LagoonBackendApplicationTests {

	private MockMvc mock;
	
	@InjectMocks
	private UserResource user;
	
	
	@Before
	public void setup() throws Exception {
		mock = MockMvcBuilders.standaloneSetup(user)
				.build();
	}
	
	@Test
	public void contextLoads() throws Exception {
		mock.perform(get("/api/auth/user/getHello"))
			.andExpect(status().isOk())
			.andExpect(content().string("hello world"));
	}
	
	@Test
	public void findUser() throws Exception {
		mock.perform(get("/api/auth/user/findUser")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userName").value("mc2994"));
	}

}
