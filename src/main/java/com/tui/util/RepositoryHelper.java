package com.tui.util;

import com.tui.exception.UserNotFoundException;
import com.tui.model.Branch;
import com.tui.model.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tui.swagger.repo.model.RepoDetailsResponseData;
import tui.swagger.repo.model.RepoDetailsResponseDataBranches;
import tui.swagger.repo.model.RepoDetailsResponseDataRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
@Slf4j
public class RepositoryHelper {
    private RestTemplate restTemplate;
    private String repoURL;
    private String userExistURL;


    public RepositoryHelper(final RestTemplate restTemplate,
                            @Value("${repository.name.url}") final String URL,
                            @Value("${repository.user.isExist}") final String userExistURL) {
        this.restTemplate = restTemplate;
        this.repoURL = URL;
        this.userExistURL = userExistURL;
    }

    public RepoDetailsResponseData readRepository(String user) {
        RepoDetailsResponseData repoDetailsResponseData = new RepoDetailsResponseData();
        Map<String, String> param = Map.of(
                "user", user
        );
        ResponseEntity<Repository[]> response = restTemplate.getForEntity(repoURL, Repository[].class, param);
        for (Repository repository : response.getBody()) {
            if (repository.getFork() == false) {
                RepoDetailsResponseDataRepositories repoDetailsResponseDataRepositories = new RepoDetailsResponseDataRepositories();
                repoDetailsResponseDataRepositories.setRepositoryName(repository.getFullName());
                repoDetailsResponseDataRepositories.repositoryOwner(repository.getOwner().getLogin());
                List<RepoDetailsResponseDataBranches> branches = listBranches(repository.getUrl() + "/branches");

                repoDetailsResponseDataRepositories.setBranches(branches);
                repoDetailsResponseData.addRepositoriesItem(repoDetailsResponseDataRepositories);

                log.info("response status :[{}]", response.getStatusCode());
            }
        }

        response.getHeaders()
                .forEach((key, value) -> log.info("header key: [{}] - value [{}]", key, value));


        if (response.getStatusCode().is2xxSuccessful()) {
            return repoDetailsResponseData;
        }
        return null;
    }

    public List<RepoDetailsResponseDataBranches> listBranches(String branchURL) {
        List<RepoDetailsResponseDataBranches> result = new ArrayList<>();
        ResponseEntity<Branch[]> responseBranches = restTemplate.getForEntity(branchURL, Branch[].class);

        if (responseBranches.getStatusCode().is2xxSuccessful()) {
            for (Branch branch : responseBranches.getBody()) {
                RepoDetailsResponseDataBranches repoDetailsBranches = new RepoDetailsResponseDataBranches();
                repoDetailsBranches.setName(branch.getName());
                repoDetailsBranches.setSha(branch.getCommit().getSha());
                result.add(repoDetailsBranches);
            }
        }
        return result;
    }

    public boolean isUserExist(String userName) {
        Map<String, String> param = Map.of(
                "user", userName
        );
        try {
            ResponseEntity<String> isUSerExist = restTemplate.getForEntity(this.userExistURL, String.class, param);
            if (isUSerExist.getStatusCode().is2xxSuccessful()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new UserNotFoundException("User Not found");
        }
    }

}