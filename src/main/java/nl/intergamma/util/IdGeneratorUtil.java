package nl.intergamma.util;

import java.util.UUID;

/**
 * Util class to generate unique ids
 */
public class IdGeneratorUtil {

    private IdGeneratorUtil() {
    }

    /**
     * Generates a new unique requestId
     *
     * @return a 32 character long alphanumeric String
     */
    public static String generateRequestId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
