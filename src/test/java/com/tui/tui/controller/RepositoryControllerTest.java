package com.tui.tui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tui.RepositoryController.RepositoryController;
import com.tui.services.impl.RepositoryDetailsServiceImpl;
import com.tui.util.JsonFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tui.swagger.repo.model.RepoDetailsResponseData;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 *
 */

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class RepositoryControllerTest {

    @Mock
    private RepositoryDetailsServiceImpl service;

    private ObjectMapper objectMapper;

    private RepoDetailsResponseData repoDetailsResponseData;

    private MockMvc mvc;

    @InjectMocks
    private RepositoryController repositoryController;

        /**
         * Setting up the test suite
         */
        @Before
        public void setUp() {
            this.mvc = MockMvcBuilders.standaloneSetup(repositoryController).build();
            objectMapper = new ObjectMapper();
            try {
                JsonFileReader jsonFileReader = new JsonFileReader(objectMapper.registerModule(new JavaTimeModule()));
                repoDetailsResponseData = jsonFileReader.readJsonFileAsObject("/test-data/Repository.json", RepoDetailsResponseData.class);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail();
            }
            MockitoAnnotations.initMocks(this);
        }

        /**
         * Read Domestic Payment Consent by ConsentId.
         */
        @Test
        public void getRepoList() {
            when(service.getRepository(anyString()))
                    .thenReturn(repoDetailsResponseData);
            try {
                mvc.perform(MockMvcRequestBuilders.get( "/v1/owner/maheshbobby/repository")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail();
            }
        }
    }

