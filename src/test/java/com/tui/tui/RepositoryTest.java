package com.tui.tui;

import com.tui.services.impl.RepositoryDetailsServiceImpl;
import com.tui.util.RepositoryHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tui.swagger.repo.model.RepoDetailsResponseData;
import tui.swagger.repo.model.RepoDetailsResponseDataRepositories;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    @Mock
    private RepositoryHelper repository;

    @InjectMocks
    private RepositoryDetailsServiceImpl repositoryDetailsService;

    @Before
    public void setUp() {

        RepoDetailsResponseData repoDetailsResponseData = new RepoDetailsResponseData();
        List<RepoDetailsResponseDataRepositories> repositoriesList = new ArrayList<>();
        RepoDetailsResponseDataRepositories repoDetailsResponseDataRepositories = new RepoDetailsResponseDataRepositories();
        repoDetailsResponseDataRepositories.setRepositoryName("maheshrepo");
        repositoriesList.add(repoDetailsResponseDataRepositories);
        repoDetailsResponseData.setRepositories(repositoriesList);
        when(repository.readRepository(anyString())).thenReturn(repoDetailsResponseData);
    }

   @Test
    public void testGetRepository(){
        RepoDetailsResponseData repoDetailsResponseData = repositoryDetailsService.getRepository("mahesh");
        assertTrue(repoDetailsResponseData.getRepositories().get(0).getRepositoryName()!=null);
        assertEquals("maheshrepo", repoDetailsResponseData.getRepositories().get(0).getRepositoryName());

    }
}
