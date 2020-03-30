package com.ldeng;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lagoon.controller.UserResource;
import com.lagoon.model.User;
import com.lagoon.service.UserService;
import com.lagoon.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class LagoonBackendApplicationTests {

	private MockMvc mock;
	
	@InjectMocks
	private UserResource user;
	
	@Autowired
	private UserService user1;
	
 	@MockBean
	private UserServiceImpl userService;
	
 	//initialize the mockmvc object
	@Before
	public void setup() throws Exception {
		mock = MockMvcBuilders.standaloneSetup(user)
				.build();
	}
 	
 	@Test
 	public void getUserTest() {
 		when(userService.findAll()).thenReturn(Stream.of(new User()).collect(Collectors.toList()));
 		assertEquals(1, user1.findAll().size());
 	}
 	
 /**	@Test
 	public void getUserByLastName() {
 		when(userService.findByLastName("Tolentino")).thenReturn("tolentino");
 		assertEquals("tolentino", user1.findByLastName("Tolentino"));
 		
 		verify(userService.findByUserName("Tolentino"));
 	} */
	
	@Test
	public void contextLoads() throws Exception {
		mock.perform(get("/api/auth/user/getHello"))
			.andExpect(status().isOk())
			.andExpect(content().string("hello world"));
	}
	
	@Test
	public void findUser() throws Exception {
//		mock.perform(get("/api/auth/user/findUser")
//				.accept(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.userName").value("mc2994"));
		
		MvcResult result = mock.perform(get("/api/auth/user/findUser")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		System.out.println(">>>>>>>>> "+result.getResponse());
		
		
	}

}
