package com.ajahcs.ulid;

import java.security.SecureRandom;
import com.ajahcs.ulid.utils.Base32;

/**
 * Ulid generates a Universally Unique Lexicographically Sortable Identifier
 * based on the spec found in this document: https://github.com/ulid/javascript/blob/master/README.md
 */
public class Ulid {

    private static SecureRandom randomNumberGenerator = new SecureRandom();
    private static long lastTimeStamp;
    private static String lastRandom;

    public Ulid(byte[] seed){
        randomNumberGenerator = new SecureRandom(seed);
    }

    public String generate(long time){
        return get(time);
    }


    private static String generateRandomPart(int length) {
        StringBuilder result = new StringBuilder();
        while (length-- > 0) {
            int position = randomNumberGenerator.nextInt(32) % 32;
            result.append(Base32.getChar(position));
        }
        return result.toString();
    }

    public static String get(long time) {
        if (time == lastTimeStamp) {
            lastRandom = Base32.increment(lastRandom, 1);
        } else {
            lastRandom = generateRandomPart(16);
        }

        lastTimeStamp = time;
        return Base32.toBase(time, 10) + lastRandom;
    }
}
