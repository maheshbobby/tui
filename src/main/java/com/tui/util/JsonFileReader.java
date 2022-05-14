package com.tui.util;

/**
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class JsonFileReader {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JsonFileReader Constructor with ObjectMapper
     *
     * @param objectMapper - the ObjectMapper for Jackson
     */
    public JsonFileReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * read the json file into a jackson-Java object
     *
     * @param jsonFilePath - the relative file path for json file
     * @param valueType    - the Class for java object
     * @param <T>          - they type to be returned
     * @return - the java object for type T
     * @throws IOException - throw IOException if something goes wrong while reading file
     */
    public <T> T readJsonFileAsObject(String jsonFilePath, Class<T> valueType) throws IOException {
        try(InputStream is = this.getClass().getResourceAsStream(jsonFilePath)) {
            return objectMapper.readValue(is, valueType);
        }
    }

}
