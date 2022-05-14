package com.tui.handler.messages;

import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class ErrorMessagesFactory {


    public <E extends ErrorMessages> Map<String, E> transformErrorMessagesFileToMap(String filename) {
        Map<String, E> result = new HashMap<String, E>();
        String line;
        String[] strarr;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(filename);
        BufferedReader fileIn = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = fileIn.readLine()) != null) {
                //putting string chunks to hashmap constructor
                strarr = line.split("\t");
                result.put(strarr[0], (E) getObj(strarr[0], strarr[1],
                        HttpStatus.valueOf(Integer.parseInt(strarr[2]))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public abstract <E extends ErrorMessages> E getObj(String code, String msg, HttpStatus status);


}
