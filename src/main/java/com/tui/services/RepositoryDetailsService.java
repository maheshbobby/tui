package com.tui.services;

import org.springframework.stereotype.Service;
import tui.swagger.repo.model.RepoDetailsResponseData;

@Service
public interface RepositoryDetailsService {
     RepoDetailsResponseData getRepository(String user);
     boolean isValidUserUser(String user);

}
