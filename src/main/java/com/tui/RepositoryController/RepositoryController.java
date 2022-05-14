package com.tui.RepositoryController;

import com.tui.exception.UserNotFoundException;
import com.tui.services.impl.RepositoryDetailsServiceImpl;
import com.tui.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;

import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import tui.swagger.repo.model.RepoDetailsResponseData;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@Slf4j
@RequestMapping(Constants.VERSION)

public class RepositoryController {
    private RepositoryDetailsServiceImpl service;

    public RepositoryController(RepositoryDetailsServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "/repository/owner/{ownerName}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RepoDetailsResponseData getRepoList(
            @RequestHeader(value = Constants.Accept) String acceptHeader,
            @RequestHeader(value = Constants.X_JOURNEYID) UUID xJourneyID,
            @RequestHeader(value = Constants.X_SOURCESYSTEMID) String xSourceSystemID,
            @RequestHeader(value = Constants.X_CREATEDATETIME)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime xCreateDateTime,
            @PathVariable String ownerName) throws HttpMediaTypeNotAcceptableException {
        log.info("ownerName:  [{}]", ownerName);
        validateHeaders(acceptHeader);
        validateUser(ownerName);
        RepoDetailsResponseData repository = service.getRepository(ownerName);
        log.info("repository:  [{}]", repository);
        return repository;

    }

    private void validateHeaders(String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        if (!acceptHeader.contentEquals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new HttpMediaTypeNotAcceptableException(acceptHeader);

        }
    }

    private void validateUser(String ownerName) throws UserNotFoundException {
        if (!service.isValidUserUser(ownerName)) {
            throw new UserNotFoundException(ownerName);
        }
    }

}

