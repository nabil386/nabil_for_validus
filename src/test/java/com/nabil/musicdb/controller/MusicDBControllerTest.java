package com.nabil.musicdb.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicDBControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(8090))
				.build();
	}
	

	@Test
	public void get() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("musicdb"));
	}
	
	@Test
	public void aNewArtist()throws Exception{
		mockMvc.perform(RestDocumentationRequestBuilders.post("/musicdb/artist/new").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"name\": \"John King\"}"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(MockMvcRestDocumentation.document("createAtrist"));
		
		mockMvc.perform(RestDocumentationRequestBuilders.post("/musicdb/artist/1/album/new").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"name\": \"Hello World\", \"yearReleased\":\"2010\"}"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(MockMvcRestDocumentation.document("createAlbum"));
		
		mockMvc.perform(RestDocumentationRequestBuilders.post("/musicdb/artist/1/album/1/song/new").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content("{\"name\": \"Hello\", \"track\":\"1\"}"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(MockMvcRestDocumentation.document("createSong"));
	}
	
	@Test
	public void getAllArtists() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/artists").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getAllArtists"));
	}

	@Test
	public void getArtistById() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/artist/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getArtistById"));
	}
	
	@Test
	public void getAllAlbums() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/albums").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getAllAlbums"));
	}

	@Test
	public void getAlbumById() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/album/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getAlbumById"));
	}

	@Test
	public void getAllSongs() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/songs").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getAllSongs"));
	}

	@Test
	public void getSongById() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/musicdb/song/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andDo(MockMvcRestDocumentation.document("getSongById"));
	}	
}
