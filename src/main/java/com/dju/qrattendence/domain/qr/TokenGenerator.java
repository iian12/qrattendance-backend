package com.dju.qrattendence.domain.qr;

import java.security.SecureRandom;
import java.util.Scanner;

public class TokenGenerator {

    private static final String CHARS = "absadfkajlkfejioajldkfjkaldjflkajlkfjladjflkajdfklasjlkfjasklfjl";
    private static final SecureRandom RANDOM = new SecureRandom();

    private TokenGenerator() {}

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }

        return sb.toString();
    }
}
