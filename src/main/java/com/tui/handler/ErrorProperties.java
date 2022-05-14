package com.tui.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorProperties.class);

    private static ErrorProperties errProp;

    /**
     * Create a static method to get instance.
     */
    public static ErrorProperties getInstance() {

        if (errProp == null) {
            errProp = new ErrorProperties();
        }
        return errProp;
    }

    /**
     * Load the properties file.
     *
     * @param prop
     */
//    public Properties loadErrProp() {
//        InputStream input;
//        Properties prop = new Properties();
//        try {
//            LOGGER.info("Loading the error messages properties file.");
//            input = ErrorProperties.class.getClassLoader().getResourceAsStream("messages.properties");
//            prop.load(input);
//        } catch (Exception e) {
//            throw new OBException("Error occured while loading the properties file.", e);
//        }
//        return prop;
//    }
}
