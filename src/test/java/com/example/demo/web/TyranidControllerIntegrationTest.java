package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Tyranid;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //sets up the MockMVC objects
@Sql(scripts = {"classpath:Tyranid-schema.sql", "classpath:Tyranid-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TyranidControllerIntegrationTest {

	@Autowired //pull the MockMvc object from the context (like with @Service or @Repository)
	private MockMvc mvc; // class that performs the request (it basically works as postman)
	
	@Autowired
	private ObjectMapper mapper;  // java <-> JSON converter that spring uses
	
	@Test
	void testCreate() throws Exception {
		Tyranid testTyranid = new Tyranid(null, "Hive Tyrant", "Hive Fleet Leviathan", 300);
		String testTyranidAsJSON=this.mapper.writeValueAsString(testTyranid);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testTyranidAsJSON);
		
		Tyranid testCreatedTyranid = new Tyranid(3, "Hive Tyrant", "Hive Fleet Leviathan", 300);
		String testCreatedTyranidAsJSON = this.mapper.writeValueAsString(testCreatedTyranid);
		ResultMatcher checkStatus = status().isCreated(); //status 201 - created
		ResultMatcher checkBody = content().json(testCreatedTyranidAsJSON); //checks if the body matches my testCreatedTyranidAsJson
		
		// sends the request and then checks the status and the body
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
	@Test
	void getAllTyranidsTest() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<Tyranid> testTyranids = List.of(new Tyranid(1, "Hive Tyrant", "Hive Fleet Leviathan", 300), new Tyranid(2, "Zoanthrope", "Hive Fleet Kraken", 60));
		String json = this.mapper.writeValueAsString(testTyranids);		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getTyranidsByNameTest() throws Exception {
		RequestBuilder req = get("/getByName/Hive Tyrant");
		
		List<Tyranid> testTyranids = List.of(new Tyranid(1, "Hive Tyrant", "Hive Fleet Leviathan", 300));
		String json = this.mapper.writeValueAsString(testTyranids);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getTyranidsByHiveFleetTest() throws Exception {
		RequestBuilder req = get("/getByHiveFleet/Hive Fleet Leviathan");
		
		List<Tyranid> testTyranids = List.of(new Tyranid(1, "Hive Tyrant", "Hive Fleet Leviathan", 300));
		String json = this.mapper.writeValueAsString(testTyranids);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getTyranidsByPointsTest() throws Exception {
		RequestBuilder req = get("/getByPoints/300");
		
		List<Tyranid> testTyranids = List.of(new Tyranid(1, "Hive Tyrant", "Hive Fleet Leviathan", 300));
		String json = this.mapper.writeValueAsString(testTyranids);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getTyranidTest() throws Exception {
		RequestBuilder req = get("/get/1");
		
		Tyranid testTyranid = new Tyranid(1, "Hive Tyrant", "Hive Fleet Leviathan", 300);
		String json = this.mapper.writeValueAsString(testTyranid);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		Tyranid testTyranid = new Tyranid(null, "Genestealer", "Hive Fleet Gorgon", 10);
		String testTyranidAsJSON=this.mapper.writeValueAsString(testTyranid);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testTyranidAsJSON);
		
		Tyranid testReplacedTyranid = new Tyranid(1, "Genestealer", "Hive Fleet Gorgon", 10);
		String testReplacedTyranidAsJSON = this.mapper.writeValueAsString(testReplacedTyranid);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testReplacedTyranidAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder req = delete("/remove/1").contentType(MediaType.APPLICATION_JSON);		
		
		ResultMatcher checkStatus = status().isNoContent(); //status 204 - no content
		
		this.mvc.perform(req).andExpect(checkStatus);
		
	}
}
