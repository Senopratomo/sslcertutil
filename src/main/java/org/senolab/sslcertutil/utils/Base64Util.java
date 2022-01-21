package org.senolab.sslcertutil.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {
    public static String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public static byte[] decode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input.replaceAll("\\s+",""));
        return decodedBytes;
    }
}
