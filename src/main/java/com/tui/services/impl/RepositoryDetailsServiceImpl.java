package com.tui.services.impl;

import com.tui.services.RepositoryDetailsService;
import com.tui.util.RepositoryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tui.swagger.repo.model.RepoDetailsResponseData;

    @Service
    @Slf4j
    public class RepositoryDetailsServiceImpl implements RepositoryDetailsService {

        private RepositoryHelper repository;

        public RepositoryDetailsServiceImpl(RepositoryHelper repository) {
            this.repository = repository;
        }

        public RepoDetailsResponseData getRepository(String user) {
            RepoDetailsResponseData repos = repository.readRepository(user);
            log.info("repositories: [{}]", repos);
            return repos;
        }

        public boolean isValidUserUser(String user) {
            return repository.isUserExist(user);
        }
}
