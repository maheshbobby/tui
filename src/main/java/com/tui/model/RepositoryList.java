
package com.tui.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RepositoryList {

    ArrayList<Repository> repositoryList = new ArrayList<>();

    public ArrayList<Repository> getRepoList() {
        return repositoryList;
    }

    public void setRepoList(ArrayList<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
