package com.example.demo;

import com.example.demo.image.Image;
import com.example.demo.image.ImageController;
import com.example.demo.image.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	ImageRepository imageRepository;

	List<Image> testImages = List.of(
			new Image(
					1L,
					"EMERGENCY: VERY CUTE PUPPY",
					"Adorable puppy, minding its business, and saving lives in the process.",
					List.of("cutie","wholesome","aww","fluffy","loml")
			),
			new Image(
					2L,
					"Skepta, Drake and Travis Scott",
					"The musicians everyone should see before they die.",
					List.of("talent","legends","loml","2022", "vision board")
			),
			new Image(
					3L,
					"Culinary perfection",
					"A huge pizza box full of Irish nachos. Student pictured drooling above it stated, “Honestly, just pay me in Irish nachos.”",
					List.of("fluffy","waffle fries","jalapeno","heaven", "dream job")
			)
	);

	@Test
	public void getAllImages() throws Exception {

		Mockito.when(imageRepository.findAll()).thenReturn(testImages);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/img")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].title", is("EMERGENCY: VERY CUTE PUPPY")))
				.andExpect(jsonPath("$[1].title", is("Skepta, Drake and Travis Scott")))
				.andExpect(jsonPath("$[2].title", is("Culinary perfection")));
	}

	@Test
	public void createImage() throws Exception {
		Image testImage = new Image(
				4L,
				"Selfie with Mom",
				"Miss her bare",
				List.of("family","homesick","bestie")
		);

		Mockito.when(imageRepository.save(testImage)).thenReturn(testImage);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post("/img")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testImage));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	public void createMultipleImages() throws Exception {

		Mockito.when(imageRepository.saveAll(testImages)).thenReturn(testImages);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.post("/img/photoDump")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testImages));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	public void searchMultipleTags() throws Exception {

		Mockito.when(imageRepository.findAll()).thenReturn(testImages);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.get("/img/tags?tags=fluffy,loml")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testImages));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].title", is("EMERGENCY: VERY CUTE PUPPY")))
				.andExpect(jsonPath("$[2].title", is("Skepta, Drake and Travis Scott")))
				.andExpect(jsonPath("$[1].title", is("Culinary perfection")));
	}

	@Test
	public void searchOneTag() throws Exception {

		Mockito.when(imageRepository.findAll()).thenReturn(testImages);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.get("/img/tag?tag=fluffy")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(testImages));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title", is("EMERGENCY: VERY CUTE PUPPY")))
				.andExpect(jsonPath("$[1].title", is("Culinary perfection")));
	}

	@Test
	public void updateDescription() throws Exception {
		Image testImage = new Image(
				5L,
				"The Moon",
				"Running out of ideas here, folks",
				List.of("shiny","pinkmoon","wonder")
		);
		Mockito.when(imageRepository.findById(testImage.getId())).thenReturn(Optional.of(testImage));

		imageRepository.save(testImage);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/img/"+testImage.getId()+"?description=crescent")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString("CRESCENT!"));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}
}
